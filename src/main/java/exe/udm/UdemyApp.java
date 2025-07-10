package exe.udm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import exe.udm.inout.FileIO;
import exe.udm.inout.GameFilter;
// import exe.udm.stream.Streams;
// import exe.udm.utils.NumericAlgorithms;
// import exe.udm.utils.VinValidator;

@SpringBootApplication
public class UdemyApp {

  public static void main(String[] args) {
    SpringApplication.run(UdemyApp.class, args);

    GameFilter f = new GameFilter.GameFilterBuilder()
      .setMinRating(9)
      .setReleasedBefore("2010")
      .setNotesRegex("pistol start")
      .build();

    // NumericAlgorithms.testNumericalAlgorithms();
    // Streams.runStreams();;
    // VinValidator.testVinValidator();
    // FileInput.runTest();
    FileIO.printGameInfo(f.satisfyAll());
  }
}
