package exe.udm.interfaces;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface Compatible<T extends Comparable<T>> {
  public boolean areCompatible(T op1, T op2);

  Consumer<String> lmb = s -> Arrays.asList(s.split(" ")).forEach(substr -> System.out.println(substr));

  UnaryOperator<String> lmb2 = str -> {
    StringBuilder sb = new StringBuilder();

    try{
      for (int i = 1; i < str.length(); i+=2) {
        sb.append(str.charAt(i));
      }
    } catch(NullPointerException npe) {

    }
    return sb.toString();
  };
}
