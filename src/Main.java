import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
  static char roundedRock = 'O';
  static char cubeRock = '#';
  static char empty = '.';
  static long result = 0;
  static long nrOfRotations = 1000;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    List<List<String>> puzzles = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int hashResult = 0;
      List<String> fullPuzzleInput = new ArrayList<>();
      HashMap<Integer, List<String>> shiftedPuzzleInput = new HashMap<>();
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          fullPuzzleInput.addAll(List.of(line.split(",")));
          System.out.println(fullPuzzleInput);
        }
      }
      for (String step : fullPuzzleInput) {
        hashResult = 0;
        char[] chars = step.toCharArray();
        for (char c : chars) {
          hashResult += (int) c;
//          System.out.println("hashResult: " + hashResult);
          hashResult *= 17;
//          System.out.println("hashResult: " + hashResult);
          hashResult = hashResult % 256;
//          System.out.println("hashResult: " + hashResult);

        }
        System.out.println("Intermediate Result: " + step + ": " + hashResult);
        result += hashResult;
      }
      System.out.println("Final Result: " + result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


