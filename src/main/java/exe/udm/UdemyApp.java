package exe.udm;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exe.udm.file.FileIO;
import exe.udm.file.game.GameFilter;
// import exe.udm.stream.Streams;
import exe.udm.threads.Threads;
import exe.udm.threads.warehouse.*;
// import exe.udm.utils.NumericAlgorithms;
// import exe.udm.utils.VinValidator;

@SpringBootApplication
public class UdemyApp {

  public static void main(String[] args) {
    SpringApplication.run(UdemyApp.class, args);

    WarehouseProcedure.execute();
    // runThreads();
    // testFileIO();

    // NumericAlgorithms.testNumericalAlgorithms();
    // Streams.runStreams();
    // VinValidator.testVinValidator();
    // FileIO.runTest();
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

  public static void runThreads() {
    Thread even = Threads.getEven();
    Thread odd = Threads.getOdd();

    try {
      odd.start();
      even.start();

      TimeUnit.SECONDS.sleep(2);
      even.interrupt();
      TimeUnit.SECONDS.sleep(4);
      odd.interrupt();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }
  }
}
