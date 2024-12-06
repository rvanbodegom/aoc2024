import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
  static char ash = '.';
  static char rock = '#';
  static int results = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    List<List<String>> puzzles = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      List<String> aPuzzleInput = new ArrayList<>();
      while ((line = br.readLine()) != null) {
        aPuzzleInput.add(line);
        if (line.isEmpty()) { // empty line found, so we have a complete puzzle input
          aPuzzleInput.removeLast();
          puzzles.add(aPuzzleInput);
          results += 100 * findHorizontalLine(aPuzzleInput);
          results += findHorizontalLine(invertMatrix(aPuzzleInput));
          System.out.println(results);
          System.out.println(aPuzzleInput);
          aPuzzleInput = new ArrayList<>();
        }
        System.out.println(line);


      }
      puzzles.add(aPuzzleInput);
//      System.out.println(aPuzzleInput);
      results += 100 * findHorizontalLine(aPuzzleInput);
//      System.out.println(aPuzzleInput);
      results += findHorizontalLine(invertMatrix(aPuzzleInput));
      System.out.println(results);
      aPuzzleInput = new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
    }
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
  public static int findHorizontalLine(List<String> puzzleInput) {
    int count = 0;
    for (int i = 0; i < puzzleInput.size(); i++) {
      if (i + 1 < puzzleInput.size() && puzzleInput.get(i).equals(puzzleInput.get(i + 1))) {
        System.out.println("found identical rows at " + i + " and " + (i + 1));
        System.out.println(puzzleInput.get(i));
        System.out.println(puzzleInput.get(i + 1));
        int step = 1;
        boolean mismatch = false;
        // two identical rows found, now move 'outwards' to see if we have a good reflection.
        System.out.println("i: " + i + " step: " + step + " puzzleInput.size(): " + puzzleInput.size());
        while (i - step >= 0 && (i + 1 + step) < puzzleInput.size()) {
          if (puzzleInput.get(i - step).equals(puzzleInput.get(i + 1 + step))) {
            System.out.println("found identical rows at " + (i - step) + " and " + (i + 1 + step));

            System.out.println(puzzleInput.get(i-step));
            System.out.println(puzzleInput.get(i + 1 + step));
            // we are still good
            step++;
          } else {
            System.out.println("mismatch in rows at " + (i - step) + " and " + (i + 1 + step));
            System.out.println(puzzleInput.get(i - step));
            System.out.println(puzzleInput.get(i + 1 + step));
            mismatch = true;
            break;
          }
        }
        if (!mismatch) {
          return (i + 1); // we have a reflection
        }
      }
    }
    return 0;
  }
}