package exe.udm.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import exe.udm.file.game.PlayedGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.TreeMap;

public class FileIO {
  public static final String GAMES_CSV_SOURCE = "src/main/resources/files/games.csv";
  public static final String GAMES_JSON_OUTPUT_TARGET = "src/main/resources/files/games.json";

  public static void runTest() {
    String path = "src/main/resources/files/poems.txt";
    File file = new File(path);

    if (!file.exists()) {
      System.out.println("File does not exist at: " + file.getAbsolutePath());
    }

    try (BufferedReader brd = new BufferedReader(new FileReader(path))) {
      char[] input = new char[10];
      int sequenceLn = 0;
      int step = 0;

      while ((sequenceLn = brd.read(input)) != -1) {
        String piece = String.valueOf(input, 0, sequenceLn);
        System.out.println("step " + step + ": " + piece);
        ++step;
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static void parseGamesAsStrings() {
    try (Scanner scn = new Scanner(new File(GAMES_CSV_SOURCE))) {
      scn.useDelimiter("\\R");

      Map<String, Integer> stats = new HashMap<>();
      stats.put("TOTAL", 0);

      long howMany = scn.tokens()
        .skip(1)
        .peek(line -> {
          int cur = stats.get("TOTAL");
          stats.put("TOTAL", ++cur);
        })
        .map(line -> line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1))
        .filter(splitLine -> splitLine[7].equals("") ? false : Integer.parseInt(splitLine[7]) >= 7)
        .map(filteredLine -> filteredLine[0])
        .sorted()
        .count();

        System.out.println("# of good games: " + howMany + "/" + stats.get("TOTAL"));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public static List<PlayedGame> parseGamesAsPOJOs() throws IOException {
    return parseGamesAsPOJOs(GAMES_CSV_SOURCE);
  }

  public static List<PlayedGame> parseGamesAsPOJOs(String path) throws IOException {
    try (Stream<String> lines = Files.lines(Paths.get(path))) {
      return lines
        .skip(1)
        .map(line -> line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1))
        .map(splitLine -> {
          String name = splitLine[0];
          String cleanName = name.replaceAll("^\"", "");
          cleanName = cleanName.replaceAll("\"$", "");

          String notes = splitLine[8] == null ? "" : splitLine[8];
          String cleanNotes = notes.replaceAll("^\"", "");
          cleanNotes = cleanNotes.replaceAll("\"$", "");

          return PlayedGame.create(
            cleanName,
            splitLine[1],
            splitLine[2].equals("FALSE") || splitLine[2].equals("n/a") ? false : true,
            splitLine[3],
            splitLine[4].equals("") ? "n/a" : splitLine[4],
            splitLine[5],
            splitLine[6].equals("") || splitLine[6].equals("n/a")
              ? 0.0f
              : Float.parseFloat(splitLine[6].substring(0, splitLine[6].length() - 1)),
            splitLine[7].equals("")
              ? -1
              : Integer.parseInt(splitLine[7]),
            cleanNotes
          );
        })
        .sorted((g1, g2) -> {
          var g1Fin = g1.finished().toLowerCase();
          var g2Fin = g2.finished().toLowerCase();

          if (g1Fin.equals(g2Fin)) {
            return g1.name().compareTo(g2.name());
          }
          if (g1Fin.equals("n/a")) {
            return 1;
          }
          if (g2Fin.equals("n/a")) {
            return -1;
          }
          return g2Fin.compareTo(g1Fin);
        })
        .toList();
    }
  }

  public static void printFilteredGameInfo(List<PlayedGame> games) {
    printFilteredGameInfo(games, game -> true);
  }

  public static void printFilteredGameInfo(List<PlayedGame> games, Predicate<PlayedGame> satisfyAll) {
    Map<Integer, List<PlayedGame>> grouped = games
      .stream()
      .filter(satisfyAll)
      .collect(Collectors.groupingBy(
        PlayedGame::rating,
        () -> new TreeMap<Integer, List<PlayedGame>>((k1, k2) -> k2 - k1),
        Collectors.toList()
      ));

    Iterator<Map.Entry<Integer, List<PlayedGame>>> itr = grouped.entrySet().iterator();

    while (itr.hasNext()) {
      var entry = itr.next();
      var rating = entry.getKey() == -1 ? "not rated" : entry.getKey();

      System.out.println("-".repeat(81));
      System.out.println("RATING: " + rating);
      System.out.println("-".repeat(81));

      entry.getValue()
        .stream()
        .forEach(System.out::println);
    }
  }

  public static void exportGameInfoToJSON(List<PlayedGame> games) {
    exportGameInfoToJSON(games, GAMES_JSON_OUTPUT_TARGET);
  }

  public static void exportGameInfoToJSON(List<PlayedGame> games, String path) {
    PlayedGame[] gamesArray = games.toArray(new PlayedGame[games.size()]);
    Arrays.sort(gamesArray, Comparator.comparing(PlayedGame::name));

    ObjectMapper jsonMapper = new ObjectMapper()
      .enable(SerializationFeature.INDENT_OUTPUT);

    try {
      jsonMapper.writeValue(
        new File(path),
        gamesArray
      );
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
