import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
  static long result = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      List<Integer> leftSide = new ArrayList<>();
      List<Integer> rightSide = new ArrayList<>();
      List<String> fullPuzzleInput = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          leftSide.add(Integer.parseInt(line.split("\\s+")[0]));
          rightSide.add(Integer.parseInt(line.split("\\s+")[1]));
          //fullPuzzleInput.addAll(List.of(line.split(",")));
          System.out.println(fullPuzzleInput);
        }
      }
      System.out.println("Left Side: " + leftSide);
      System.out.println("Right Side: " + rightSide);
      Collections.sort(leftSide);
      Collections.sort(rightSide);
      System.out.println("Left Side: " + leftSide);
      System.out.println("Right Side: " + rightSide);
      // calculate the distance between each element of leftSide and rightSide
      for (int i = 0; i < leftSide.size(); i++) {
        //result += Math.abs(leftSide.get(i) - rightSide.get(i));
      }
      for(int left: leftSide) {
        // count the number of occurences of this number in the right side
        result += left*Collections.frequency(rightSide, left);
        System.out.println("Left: " + left + " Right: " + Collections.frequency(rightSide, left));

      }



      System.out.println("Final Result: " + result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}


