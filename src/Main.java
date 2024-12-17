import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
  static long result = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      List<List<Integer>> reports = new ArrayList<>();
      //List<Integer> rightSide = new ArrayList<>();
      List<String> fullPuzzleInput = new ArrayList<>();
      List<List<Integer>> unsafeReports = new ArrayList<>();
      String fullInput = "";
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          fullInput = fullInput + line;

        }
      }

      System.out.println(fullInput);
      String doRegex = "do\\(\\)";
      String dontRegex = "don't\\(\\)";


      String regex2 = dontRegex + "(.*?)" + doRegex;
      Pattern pattern2 = Pattern.compile(regex2);
      Matcher matcher2 = pattern2.matcher(fullInput);

      //remove all patterns matching regex2
      while (matcher2.find()) {
        fullInput = fullInput.replace(matcher2.group(1), "");
      }
      System.out.println("fullInput after removing dont(do): " + fullInput);

      // find each occurence of mul(x,x) where x is a 1-3 digit integer within line
      String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(fullInput);

      while (matcher.find()) {
        int x1 = Integer.parseInt(matcher.group(1));
        int x2 = Integer.parseInt(matcher.group(2));
        int product = x1 * x2;
        System.out.println("Found: " + matcher.group() + " -> Product: " + product);
        result += product;
      }


      System.out.println("Final Result: " + result);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


