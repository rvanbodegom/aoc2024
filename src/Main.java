import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


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
      // boxnr, label, focalLength
      List<Map><String, Integer>[] lenses = new LinkedHashMap<>();
      for (String step : fullPuzzleInput) {
        hashResult = 0;
        String label;
        if (step.contains("=")) {
          label = step.split("=")[0];
          for (char c : label.toCharArray()) {
            hashResult += (int) c;
          }
          hashResult *= 17;
          hashResult = hashResult % 256;
          // add to box
          lenses.put(hashResult, new Lens(label, Integer.parseInt(step.split("=")[1])));
        } else { // "-"
          hashResult = 0;
          label = step.split("-")[0];
          for (char c : label.toCharArray()) {
            hashResult += (int) c;
          }
          hashResult *= 17;
          hashResult = hashResult % 256;
          if (lenses.containsKey(hashResult)) {
            Lens tmp = lenses.get(hashResult); // get the lens
            if (tmp.)
            lenses.remove();
          }
        }

      }
      System.out.println("Final Result: " + result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}


