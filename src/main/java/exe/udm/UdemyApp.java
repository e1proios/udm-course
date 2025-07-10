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

    GameFilter f = new GameFilter.GameFilterBuilder()
      .setMinRating(9)
      .setReleasedBefore("2010")
      .setNotesRegex("pistol start")
      .build();

    try {
      var games = FileIO.parseGamesAsPOJOs();
      FileIO.printGameInfo(games, f.satisfyAll());
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    // NumericAlgorithms.testNumericalAlgorithms();
    // Streams.runStreams();;
    // VinValidator.testVinValidator();
    // FileInput.runTest();
  }
}
