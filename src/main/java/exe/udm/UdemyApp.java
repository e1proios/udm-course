package exe.udm;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exe.udm.inout.FileIO;
import exe.udm.inout.game.GameFilter;

@SpringBootApplication
public class UdemyApp {

  public static void main(String[] args) {
    SpringApplication.run(UdemyApp.class, args);

    testFileIO();

    // NumericAlgorithms.testNumericalAlgorithms();
    // Streams.runStreams();;
    // VinValidator.testVinValidator();
    // FileInput.runTest();
  }

  public static void testFileIO() {
    GameFilter f = new GameFilter.GameFilterBuilder()
      .setMinRating(9)
      .setReleasedBefore("2010")
      .setNotesRegex("pistol start")
      .build();

    try {
      var games = FileIO.parseGamesAsPOJOs();
      FileIO.printFilteredGameInfo(games, f.satisfyAll());
      FileIO.exportGameInfoToJSON(games);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
