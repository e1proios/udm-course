package exe.udm.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

// additional German checksum used in vehicle registration docs
public class VinValidator {
  private static final Pattern VIN_REGEX = Pattern.compile("(?i)^[a-hj-npr-z0-9]{14}[0-9]{3}$");

  private static final Map<Character, Integer> VIN_CHARS_VALUE_MAP = new HashMap<>(Map.ofEntries(
    Map.entry('A', 1),
    Map.entry('B', 2),
    Map.entry('C', 3),
    Map.entry('D', 4),
    Map.entry('E', 5),
    Map.entry('F', 6),
    Map.entry('G', 7),
    Map.entry('H', 8),
    Map.entry('J', 1),
    Map.entry('K', 2),
    Map.entry('L', 3),
    Map.entry('M', 4),
    Map.entry('N', 5),
    Map.entry('P', 7),
    Map.entry('R', 9),
    Map.entry('S', 2),
    Map.entry('T', 3),
    Map.entry('U', 4),
    Map.entry('V', 5),
    Map.entry('W', 6),
    Map.entry('X', 7),
    Map.entry('Y', 8),
    Map.entry('Z', 9)
  ));

  private static final int[] VIN_CHARS_WEIGHT_SEQUENCE = {9, 8, 7, 6, 5, 4, 3, 2, 10, 9, 8, 7, 6, 5, 4, 3, 2};

  private static char calculateVinChecksum(String vin) {
    int weightedSum = 0;

    for (int i = 0, ln = vin.length(); i < ln; ++i) {
      char cur = vin.charAt(i);
      int numVal = Character.getNumericValue(cur);
      int charVal;

      if (numVal >= 0 && numVal < 10) {
        charVal = numVal;
      } else {
        charVal = VinValidator.VIN_CHARS_VALUE_MAP.get(cur);
      }
      weightedSum += charVal * VinValidator.VIN_CHARS_WEIGHT_SEQUENCE[i];
    }
    int mod = weightedSum % 11;
    return mod == 10 ? 'X' : Character.forDigit(mod, 10);
  }

  public static boolean testVin(String vin, char checksum) {
    if (vin.length() != 17) {
      System.out.println(vin + " - invalid VIN length!");
      return false;
    }
    if (VinValidator.VIN_REGEX.matcher(vin).matches()) {
      boolean isValid = checksum == VinValidator.calculateVinChecksum(vin);

      if (isValid) {
        System.out.println(checksum + " is a valid checksum for VIN: " + vin);
      } else {
        System.out.println(checksum + " is not a valid checksum for VIN: " + vin);
      }

      return isValid;
    }
    System.out.println(vin + " - invalid VIN shape!");
    return false;
  }

  public static final void testVinValidator() {
    Map<String, Character> testVins = new HashMap<>(Map.of(
      "WMWXR51080TM29088", '5', // correct checksum: 5
      "WMWXR51080TM29AAA", '0', // invalid shape
      "WMWXR51080TM29IOQ", '0', // invalid shape
      "WMWXR51080TM29", '0', // invalid shape
      "5N1AN0NU6BC506916", 'X', // correct checksum: 1
      "WBA3A5C57CF256651", '3' // correct checksum: 3
    ));

    testVins.forEach(VinValidator::testVin);
  }
}
