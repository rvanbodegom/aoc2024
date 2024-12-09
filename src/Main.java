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
  static long results = 0;
  static long nrOfRotations = 1000;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    List<List<String>> puzzles = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      List<String> fullPuzzleInput = new ArrayList<>();
      HashMap<Integer, List<String>> shiftedPuzzleInput = new HashMap<>();
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          fullPuzzleInput.add(line);
          System.out.println(line);
        }
      }
      //fullPuzzleInput.removeLast(); // remove last empty line
      for (int r = 0; r < 4 * nrOfRotations; r++) {
//        fullPuzzleInput = invertMatrix(fullPuzzleInput);
//        List<String> singleLineList = new ArrayList<>();
        if (r%100000 == 0) {
          System.out.println("r: " + r);
        }

//        System.out.println("fpi:" + fullPuzzleInput);
        if (r%4 == 0) {
//          System.out.println("UP");
          // now shift up all roundedRocks until they hit either another roundedRock or a cubeRock
          for (int col = 0; col < fullPuzzleInput.get(0).length(); col++) {
            for (int row = 0; row < fullPuzzleInput.size(); row++) {
              char[] chars = fullPuzzleInput.get(row).toCharArray();
              if (chars[col] == roundedRock) {
                int k = row;
                while (k > 0 && fullPuzzleInput.get(k - 1).charAt(col) == empty) {
                  chars = fullPuzzleInput.get(k).toCharArray();
                  char[] aboveChars = fullPuzzleInput.get(k - 1).toCharArray();
                  aboveChars[col] = roundedRock;
                  chars[col] = empty;
                  fullPuzzleInput.set(k, new String(chars));
                  fullPuzzleInput.set(k - 1, new String(aboveChars));
                  k--;
                }
              }
            }
          }
//          for (String inputLine : fullPuzzleInput) {
//            System.out.println(inputLine);
//          }
        }
        // now shift left all roundedRocks until they hit either another roundedRock or a cubeRock
        if (r%4 == 1) {
//          System.out.println("LEFT");
          for (int l = 0; l < fullPuzzleInput.size(); l++) {
            String singleLine = fullPuzzleInput.get(l);
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
            fullPuzzleInput.set(l, new String(chars));
          }
//          for (String inputLine : fullPuzzleInput) {
//            System.out.println(inputLine);
//          }
        }

        if (r%4 == 2) { // SOUTH

//          System.out.println("SOUTH");
          // now shift down all roundedRocks until they hit either another roundedRock or a cubeRock

          for (int col = 0; col < fullPuzzleInput.get(0).length(); col++) {
            for (int row = fullPuzzleInput.size() - 1; row >= 0; row--) {
              char[] chars = fullPuzzleInput.get(row).toCharArray();
              if (chars[col] == roundedRock) {
                int k = row;
                while (k < fullPuzzleInput.get(0).length() - 1 && fullPuzzleInput.get(k + 1).charAt(col) == empty) {
                  chars = fullPuzzleInput.get(k).toCharArray();
                  char[] belowChars = fullPuzzleInput.get(k + 1).toCharArray();
                  belowChars[col] = roundedRock;
                  chars[col] = empty;
                  fullPuzzleInput.set(k, new String(chars));
                  fullPuzzleInput.set(k + 1, new String(belowChars));
                  k++;
                }
              }
            }
          }
//          for (String inputLine : fullPuzzleInput) {
//            System.out.println(inputLine);
//          }
        }

        // now shift right all roundedRocks until they hit either another roundedRock or a cubeRock
        if (r%4 == 3) {
//          System.out.println("RIGHT");
          for (int l = 0; l < fullPuzzleInput.size(); l++) {
            String singleLine = fullPuzzleInput.get(l);
            char[] chars = singleLine.toCharArray();
            for (int i = chars.length - 1; i >= 0; i--) {
              if (chars[i] == roundedRock) {
                int j = i;
                while (j < chars.length - 1 && chars[j + 1] == empty) {
                  chars[j + 1] = roundedRock;
                  chars[j] = empty;
                  j++;
                }
              }
            }
            fullPuzzleInput.set(l, new String(chars));
          }
//          for (String inputLine : fullPuzzleInput) {
//            System.out.println(inputLine);
//          }
        }
      }

      // DONE
      System.out.println("DONE");
      for (int i = 0; i < fullPuzzleInput.size(); i++) {
        System.out.println("size: " + fullPuzzleInput.size());
        String inputLine = fullPuzzleInput.get(i);
        System.out.println(inputLine);
        results += calculateSingleLineWeight(inputLine.toCharArray(), fullPuzzleInput.size()-i);
      }
      System.out.println("results: " + results);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
   * This method should calculate the weight of a single line, the weight is calculated by taking the sum of the
   *  distance from each roundedRock to the end of the string
   */
  public static long calculateSingleLineWeight(char[] chars, int weight) {
    long totalWeight = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == roundedRock) {
        totalWeight += weight;
//        System.out.println("weight: " + weight);
      }
    }
    return totalWeight;
  }
}


