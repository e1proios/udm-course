package exe.udm.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NumericAlgorithms {
  public static String primeFactors(int n) {
    return NumericAlgorithms.primeFactors(n, false);
  }

  public static String primeFactors(int n, boolean log) {
    int input = Math.abs(n);
    int factorLowerBound = 2;
    int factorUpperBound = input;
    List<Integer> fct = new ArrayList<>();

    int cycleCounter = 0;

    do {
      factorUpperBound = (int) Math.floor(Math.sqrt(input));

      for(int i = factorLowerBound; i <= input; ++i) {
        ++cycleCounter;
        System.out.println("i: " + i + ", cycles: " + cycleCounter);

        if (i > factorUpperBound) {
          if (log) {
            // System.out.println("i: " + i + ", cycles: " + cycleCounter);
            System.out.println("found the last prime factor: " + input);
          }
          fct.add(input);
          input = 1;
          break;
        }
        if (input % i == 0) {
          if (log) {
            // System.out.println("i: " + i + ", cycles: " + cycleCounter);
            System.out.println("found a prime factor: " + i);
          }
          factorLowerBound = i;
          fct.add(i);
          input /= i;
          break;
        }
      }
    } while (input > 1);

    return fct.toString();
  }

  public static Set<Integer> divisors(int n) {
    return NumericAlgorithms.divisors(n, false);
  }

  public static Set<Integer> divisors(int n, boolean log) {
    int input = Math.abs(n);
    int div = 2;
    int upperBound = input;
    Set<Integer> divisors = new TreeSet<>();

    if (input == 0) {
      if (log) {
        System.out.println("all nonzero integers are divisors of 0");
      }
      return divisors;
    }

    divisors.add(1);
    divisors.add(input);

    while (div < upperBound) {
      int loopResult = input / div;

      if (input % div == 0) {
        divisors.add(div);
        divisors.add(loopResult);
        upperBound = loopResult;
      }

      ++div;
    };

    if (log) {
      System.out.println(divisors.toString());
    }
    return divisors;
  }

  public static boolean isPerfect(int n) {
    if (n < 1) {
      return false;
    }

    int sum = 0;
    Set<Integer> properDivisors = NumericAlgorithms.divisors(n);
    properDivisors.remove(n);

    for (int div : properDivisors) {
      sum += div;
    }
    return sum == n;
  }

  public static int gcd(int a, int b) {
    int inputA = Math.abs(a);
    int inputB = Math.abs(b);

    if (inputA == 0 && inputB == 0) {
      return -1;
    }
    if (inputA == 0) {
      return Math.abs(b);
    }
    if (inputB == 0) {
      return Math.abs(a);
    }
    if (inputA == inputB) {
      return inputA;
    }

    Set<Integer> divisorsOfA = NumericAlgorithms.divisors(inputA, true);
    Set<Integer> divisorsOfB = NumericAlgorithms.divisors(inputB, true);

    Set<Integer> search = new TreeSet<>((first, second) -> second - first);

    Set<Integer> compare = new TreeSet<>();
    Iterator<Integer> searchIterator;
    int result = 1;

    if (inputA < inputB) {
      search.addAll(divisorsOfA);
      compare.addAll(divisorsOfB);
    } else {
      search.addAll(divisorsOfB);
      compare.addAll(divisorsOfA);
    }
    searchIterator = search.iterator();

    while (searchIterator.hasNext()) {
      Integer nxt = searchIterator.next();

      if (compare.contains(nxt)) {
        result = nxt;
        break;
      }
    }
    return result;
  }

  public static final void testNumericalAlgorithms() {
    System.out.println("Prime factors of 15: " + NumericAlgorithms.primeFactors(15));
    System.out.println("Prime factors of 225: " + NumericAlgorithms.primeFactors(225));
    System.out.println("Prime factors of 1337: " + NumericAlgorithms.primeFactors(1337));
    System.out.println("Prime factors of 191: " + NumericAlgorithms.primeFactors(191));
    // System.out.println("Prime factors of 123456789: " + NumericAlgorithms.primeFactors(123456789, true));
    System.out.println("Prime factors of -1000: " + NumericAlgorithms.primeFactors(-1000, true));
    System.out.println("Divisors of 500: " + NumericAlgorithms.divisors(1000));
    System.out.println("The greatest common divisor of 1337 and 69: " + NumericAlgorithms.gcd(69, 1337));
    System.out.println("The greatest common divisor of 500 and 750: " + NumericAlgorithms.gcd(500, 750));
    System.out.println("The greatest common divisor of 169 and 143: " + NumericAlgorithms.gcd(169, 143));
    System.out.println("8128" + (NumericAlgorithms.isPerfect(8128) ? " is" : " is not") + " a perfect number.");
    System.out.println("33,550,336" + (NumericAlgorithms.isPerfect(33550336) ? " is" : " is not") + " a perfect number");
  }
}
