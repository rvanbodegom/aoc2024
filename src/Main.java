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
      Map<Integer, List<Integer>> pageRules = new HashMap<>();
      List<List<Integer>> updates = new ArrayList<>();
      List<List<Integer>> incorrectUpdates = new ArrayList<>();
      int lineNo = 0;
      boolean firstSection = true;
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          if (firstSection) {
            String[] parts = line.split("\\|");
            List<Integer> tmp = pageRules.get(Integer.parseInt(parts[0]));
            if (tmp == null) {
              tmp = new ArrayList<>();
            }
            tmp.add(Integer.parseInt(parts[1]));
            pageRules.put(Integer.parseInt(parts[0]), tmp);
          } else {
            // split the comma separated values into a list of integers
            List<Integer> update = Arrays.stream(line.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            updates.add(update);

          }

        } else {
          firstSection = false;
          continue;
        }
      }
      for (Map.Entry<Integer, List<Integer>> entry : pageRules.entrySet()) {
        List<Integer> tmp = entry.getValue();
        Collections.sort(tmp);
        System.out.println("Key: " + entry.getKey() + " Value: " + tmp);
      }

      //System.out.println("Page Rules: " + pageRules.sort());
//      System.out.println("Updates: " + updates);
      // for each update check if the pagerules apply
      boolean reset = true;
      boolean identicalResult = true;
      for (List<Integer> update : updates) {
        boolean ruleApplies = true;
        for (int i = 0; i < update.size() - 1; i++) {
          if (reset && !identicalResult) {
            i = 0;
            reset = false;
            identicalResult = true;
          }
          Integer key = update.get(i);
//          System.out.println("Key: " + key + " with I: " + i);
          for (int j = i + 1; j < update.size(); j++) {
            if (pageRules.get(key) == null) {
//              System.out.println("Rule does not apply for update: " + update);
              System.out.println("=== Key: " + key + " does not exist in pageRules");
              // key has to be the last element in the update
              update.remove(key);
              update.add(key);

              ruleApplies = false;
              identicalResult = false;
              break;
            }
            if (!pageRules.get(key).contains(update.get(j))) {
//              System.out.println("Original Rule does not apply for update: " + update + " key  " + key + " and " + update.get(j));
              // swap positions
              int tmp = update.get(i);
              update.set(i, update.get(j));
              update.set(j, tmp);

              ruleApplies = false;
              identicalResult = false;
              break;
            } else {
              //System.out.println("== Rule applies for update: " + update + " key  " + key + " and " + update.get(j));
              reset = true;
            }
          }
        }
        if (!ruleApplies) {
          incorrectUpdates.add(update);
          System.out.println("Roel does not apply for update: " + update);

        }
      }
      //System.out.println("Incorrect Updates: " + incorrectUpdates);
      for (List<Integer> update : incorrectUpdates) {
        // set in the correct order


        int middle = update.get(update.size() / 2);
        System.out.println("Middle: " + middle);
        result += middle;
      }
      System.out.println("Final Result: " + result);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


