package exe.udm.stream;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

  public static final void runStreams() {
    var firstStream = IntStream
      .rangeClosed(1,15)
      .boxed()
      .map(i -> "B-" + i);

      var secondStream = Stream
        .iterate(16, i -> i + 1)
        .limit(15)
        .map(i -> "I-" + i);

    var streamWithAnonSupplier = Stream.generate(new Supplier<Integer>() {
      private int inc = 30;

      @Override
      public Integer get() {
          return ++this.inc;
      }
    }).limit(15).map(i -> "N-" + i);

    Streams.consumeAllStreams(List.of(
      firstStream,
      secondStream,
      streamWithAnonSupplier
    ));
  }

  public static void consumeAllStreams (List<Stream<String>> streams) {
    List<String> result = streams
      .stream()
      .flatMap(s -> s)
      .collect(Collectors.toList());

    System.out.println(result);
  }
}
