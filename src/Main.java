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
          // find 2 occurences of the char 'M' with a distance of 2
          if (input.get(i).get(j) == 'M') {
            // find if there is an adjacent cell with the char 'M'
            if (j + 2 < input.get(i).size() && input.get(i).get(j + 2) == 'M') { // check right
              // find if there is an 'A' in between and above
              if (i - 1 >= 0 && input.get(i - 1).get(j + 1) == 'A') { // above
                if (i - 2 >= 0 && input.get(i - 2).get(j + 2) == 'S' && input.get(i - 2).get(j) == 'S') { // above
                  System.out.println("Found a upward match at: " + i + " " + j + "/" + i + " " + (j + 2));
                  result++;
                }
              }
              // same for below
              if (i + 1 < input.size() && input.get(i + 1).get(j + 1) == 'A') { // below
                if (i + 2 < input.size() && input.get(i + 2).get(j + 2) == 'S' && input.get(i + 2).get(j) == 'S') { // below
                  System.out.println("Found a downward match at: " + i + " " + j + "/" + i + " " + (j + 2));
                  result++;
                }
              }
            }
            // same check but with the M below
            if (i + 2 < input.size() && input.get(i + 2).get(j) == 'M') { // check down
              // now check right
              if (j + 1 < input.size() && input.get(i + 1).get(j + 1) == 'A') { // right
                if (j + 2 < input.size() && input.get(i + 2).get(j + 2) == 'S' && input.get(i).get(j + 2) == 'S') { // right
                  System.out.println("Found a rightward match at: " + i + " " + j + "/" + (i + 2) + " " + (j + 2));
                  result++;
                }
              }
              // now go left
              if (j - 1 >= 0 && input.get(i + 1).get(j - 1) == 'A') { // left
                if (j - 2 >= 0 && input.get(i + 2).get(j - 2) == 'S' && input.get(i).get(j - 2) == 'S') { // left
                  System.out.println("Found a leftward match at: " + i + " " + j + "/" + (i + 2) + " " + (j - 2));
                  result++;
                }
              }
            }
          }
          //System.out.print(input.get(i).get(j));
        }
      }
      // System.out.println("\n");

      System.out.println("Final Result: " + result);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


