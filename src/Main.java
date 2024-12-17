import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
  static long result = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int lineNo = 0;
      List<List<Character>> input = new ArrayList<>();
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          input.add(line.chars().mapToObj(e -> (char) e).toList());
          lineNo++;
        }
      }
      for (int i = 0; i < input.size(); i++) {
        for (int j = 0; j < input.get(i).size(); j++) {
          if (input.get(i).get(j) == 'X') {
            // find if there is an adjacent cell with the char 'M'
            // if there is, increment the result by 1
            if (i - 1 >= 0 && input.get(i - 1).get(j) == 'M') { // above
              if (i - 2 >= 0 && input.get(i - 2).get(j) == 'A') { // above
                if (i - 3 >= 0 && input.get(i - 3).get(j) == 'S') { // above
                  System.out.println("Found upward XMAS, from " + i + " " + j + " to " + (i - 3) + " " + j);
                  result++;
                }
              }
            }
            if (i + 1 < input.size() && input.get(i + 1).get(j) == 'M') { // below
              if (i + 2 < input.size() && input.get(i + 2).get(j) == 'A') { // below
                if (i + 3 < input.size() && input.get(i + 3).get(j) == 'S') { // below
                  System.out.println("Found downward XMAS, from " + i + " " + j + " to " + (i + 3) + " " + j);
                  result++;
                }
              }
            }
            if (j - 1 >= 0 && input.get(i).get(j - 1) == 'M') { // left
              if (j - 2 >= 0 && input.get(i).get(j - 2) == 'A') { // left
                if (j - 3 >= 0 && input.get(i).get(j - 3) == 'S') { // left
                  System.out.println("Found leftward XMAS, from " + i + " " + j + " to " + i + " " + (j - 3));
                  result++;
                }
              }
            }
            if (j + 1 < input.get(i).size() && input.get(i).get(j + 1) == 'M') { // right
              if (j + 2 < input.get(i).size() && input.get(i).get(j + 2) == 'A') { // right
                if (j + 3 < input.get(i).size() && input.get(i).get(j + 3) == 'S') { // right
                  System.out.println("Found rightward XMAS, from " + i + " " + j + " to " + i + " " + (j + 3));
                  result++;
                }
              }
            }
            // now the same for all diagonal directions
            if (i - 1 >= 0 && j - 1 >= 0 && input.get(i - 1).get(j - 1) == 'M') { // above left
              if (i - 2 >= 0 && j - 2 >= 0 && input.get(i - 2).get(j - 2) == 'A') { // above left
                if (i - 3 >= 0 && j - 3 >= 0 && input.get(i - 3).get(j - 3) == 'S') { // above left
                  System.out.println("Found upward leftward XMAS, from " + i + " " + j + " to " + (i - 3) + " " + (j - 3));
                  result++;
                }
              }
            }
            if (i - 1 >= 0 && j + 1 < input.get(i).size() && input.get(i - 1).get(j + 1) == 'M') { // above right
              if (i - 2 >= 0 && j + 2 < input.get(i).size() && input.get(i - 2).get(j + 2) == 'A') { // above right
                if (i - 3 >= 0 && j + 3 < input.get(i).size() && input.get(i - 3).get(j + 3) == 'S') { // above right
                  System.out.println("Found upward rightward XMAS, from " + i + " " + j + " to " + (i - 3) + " " + (j + 3));
                  result++;
                }
              }
            }
            if (i + 1 < input.size() && j - 1 >= 0 && input.get(i + 1).get(j - 1) == 'M') { // below left
              if (i + 2 < input.size() && j - 2 >= 0 && input.get(i + 2).get(j - 2) == 'A') { // below left
                if (i + 3 < input.size() && j - 3 >= 0 && input.get(i + 3).get(j - 3) == 'S') { // below left
                  System.out.println("Found downward leftward XMAS, from " + i + " " + j + " to " + (i + 3) + " " + (j - 3));
                  result++;
                }
              }
            }
            if (i + 1 < input.size() && j + 1 < input.get(i).size() && input.get(i + 1).get(j + 1) == 'M') { // below right
              if (i + 2 < input.size() && j + 2 < input.get(i).size() && input.get(i + 2).get(j + 2) == 'A') { // below right
                if (i + 3 < input.size() && j + 3 < input.get(i).size() && input.get(i + 3).get(j + 3) == 'S') { // below right
                  System.out.println("Found downward rightward XMAS, from " + i + " " + j + " to " + (i + 3) + " " + (j + 3));
                  result++;
                }
              }
            }


          }
          //System.out.print(input.get(i).get(j));
        }
       // System.out.println("\n");
      }
      System.out.println("Final Result: " + result);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


