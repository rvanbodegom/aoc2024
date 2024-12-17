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
      System.out.println("Page Rules: " + pageRules);
      System.out.println("Updates: " + updates);
      // for each update check if the pagerules apply
      for (List<Integer> update : updates) {
        boolean ruleApplies = true;
        for (int i = 0; i < update.size() - 1; i++) {
          Integer key = update.get(i);
          System.out.println("Key: " + key);
          for (int j = i + 1; j < update.size(); j++) {
            if (pageRules.get(key) == null) {
              System.out.println("Rule does not apply for update: " + update);
//              System.out.println("=== Key: " + key + " does not exist in pageRules");
              ruleApplies = false;
              break;
            }
            if (!pageRules.get(key).contains(update.get(j))) {
              System.out.println("Rule does not apply for update: " + update);
              ruleApplies = false;
              break;
            }
          }
//
//          List<Integer> rules = pageRules.get(i);
//          if (rules != null) {
//            if (!rules.contains(update.get(i))) {
//              System.out.println("Rule does not apply for update: " + update);
//              ruleApplies = false;
//              break;
//            }
//          }
        }
        if (ruleApplies) {
          // find the middle element of the update rules
          int middle = update.get(update.size() / 2);
          System.out.println("Middle: " + middle);
          result+= middle;
        }
      }

      System.out.println("Final Result: " + result);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


