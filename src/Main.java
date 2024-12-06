import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static char operational = '.';
  static char damaged = '#';
  static char unknown = '?';
  static String contiguousGroupsOfDamagedSprings;
  static int results = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    List<Integer> intList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] strings = line.split(" ");
        String springLayout = strings[0];
        contiguousGroupsOfDamagedSprings = strings[1];
        // do the copying for part 2
        springLayout = springLayout.concat("?").concat(springLayout).concat("?").concat(springLayout).concat("?").concat(springLayout).concat("?").concat(springLayout);
        contiguousGroupsOfDamagedSprings = contiguousGroupsOfDamagedSprings.concat(",").concat(contiguousGroupsOfDamagedSprings).concat(",").concat(contiguousGroupsOfDamagedSprings).concat(",").concat(contiguousGroupsOfDamagedSprings).concat(",").concat(contiguousGroupsOfDamagedSprings);

        // generate the indices within springlayout which are unknown
        List<Integer> unknownIndices = new ArrayList<>();
        for (int i = 0; i < springLayout.length(); i++) {
          if (springLayout.charAt(i) == unknown) {
            unknownIndices.add(i);
          }
        }
        // generate all possible permutations of layouts with unknowns replaced by operational or damaged
        generatePermutations(springLayout, unknownIndices, 0);
        System.out.println(results);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void calculateAndCompareContiquousGroups(String springLayout) {
    System.out.println(springLayout);
    if (getDamagedSubstringsLengths(springLayout).equals(contiguousGroupsOfDamagedSprings)) {
//      System.out.println("Matched");
      results++;
    }
    //System.out.println(contiguousGroupsOfDamagedSprings);
  }
  private static String getDamagedSubstringsLengths(String springLayout) {
    List<Integer> lengths = new ArrayList<>();
    int count = 0;

    for (int i = 0; i < springLayout.length(); i++) {
      if (springLayout.charAt(i) == damaged) {
        count++;
      } else {
        if (count > 0) {
          lengths.add(count);
          count = 0;
        }
      }
    }
    // Add the last count if the string ends with damaged characters
    if (count > 0) {
      lengths.add(count);
    }

    return lengths.stream()
        .map(String::valueOf)
        .reduce((a, b) -> a + "," + b)
        .orElse("");
  }

  private static void generatePermutations(String springLayout, List<Integer> unknownIndices, int index) {
    if (index == unknownIndices.size()) {
      calculateAndCompareContiquousGroups(springLayout);

      return;
    }

    StringBuilder sb = new StringBuilder(springLayout);
    sb.setCharAt(unknownIndices.get(index), operational);
    generatePermutations(sb.toString(), unknownIndices, index + 1);

    sb.setCharAt(unknownIndices.get(index), damaged);
    generatePermutations(sb.toString(), unknownIndices, index + 1);
  }
}