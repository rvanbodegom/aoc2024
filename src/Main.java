import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static char roundedRock = 'O';
  static char cubeRock = '#';
  static char empty = '.';
  static int results = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    List<List<String>> puzzles = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      List<String> fullPuzzleInput = new ArrayList<>();
      List<String> shiftedPuzzleInput = new ArrayList<>();
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          fullPuzzleInput.add(line);
          System.out.println(line);
        }
      }
      //fullPuzzleInput.removeLast(); // remove last empty line
      fullPuzzleInput = invertMatrix(fullPuzzleInput);
      // now shift left all roundedRocks until they hit either another roundedRock or a cubeRock
      for (String singleLine : fullPuzzleInput) {
        char[] chars = singleLine.toCharArray();
        for (int i = 0; i < chars.length; i++) {
          if (chars[i] == roundedRock) {
            int j = i;
            while (j > 0 && chars[j - 1] == empty) {
              chars[j - 1] = roundedRock;
              chars[j] = empty;
              j--;
            }
          }
        }
        results += calculateSingleLineWeight(chars);
        singleLine = Arrays.toString(chars);
        shiftedPuzzleInput.add(singleLine);
        System.out.println(singleLine);
        System.out.println("results: " + results);
      }

      System.out.println("shifted:" + shiftedPuzzleInput);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * This method should calculate the weight of a single line, the weight is calculated by taking the sum of the
   *  distance from each roundedRock to the end of the string
   */
  public static int calculateSingleLineWeight(char[] chars) {
    int weight = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == roundedRock) {
        weight += chars.length - i;
        System.out.println("weight: " + weight);
      }
    }
    return weight;
  }

  public static List<String> invertMatrix(List<String> input) {
    int maxLength = input.stream().mapToInt(String::length).max().orElse(0);
    List<String> inverted = new ArrayList<>();

    for (int i = 0; i < maxLength; i++) {
      StringBuilder sb = new StringBuilder();
      for (String s : input) {
        if (i < s.length()) {
          sb.append(s.charAt(i));
        } else {
          sb.append(' '); // Fill with space if the string is shorter
        }
      }
      inverted.add(sb.toString());
    }

    return inverted;
  }

}