package exe.udm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import exe.udm.utils.NumericAlgorithms;
// import exe.udm.utils.VinValidator;
import exe.udm.stream.Streams;

@SpringBootApplication
public class UdemyApp {

	public static void main(String[] args) {
		SpringApplication.run(UdemyApp.class, args);

		// NumericAlgorithms.testNumericalAlgorithms();
		// VinValidator.testVinValidator();
		Streams.runStreams();;
	}
}
