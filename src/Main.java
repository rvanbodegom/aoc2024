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
      List<List<Integer>> reports = new ArrayList<>();
      //List<Integer> rightSide = new ArrayList<>();
      List<String> fullPuzzleInput = new ArrayList<>();
      List<List<Integer>> unsafeReports = new ArrayList<>();

      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          System.out.println("=====================================");
          boolean safe = true;
          List<Integer> report = new ArrayList<>();
          for (String str : line.split("\\s+")) {
            report.add(Integer.parseInt(str));
          }
          reports.add(report);
          System.out.println(report);


          // check if the numbers in report are in ascending order
          if (isAscending(report)) {
            System.out.println("Ascending order");
            // check if the difference between adjacent numbers is less than 3
            for (int i = 0; i < report.size() - 1; i++) {
              if (Math.abs(report.get(i + 1) - report.get(i)) > 3) {
                System.out.println("UNSAFE Difference between adjacent numbers is greater than 3");
                unsafeReports.add(report);
                safe = false;
                break;
              }
            }
            if (safe) {
              System.out.println("SAFE Difference between adjacent numbers is less than 3");
              result++;
              continue;
            }
          } else if (isDescending(report)) {
            System.out.println("Descending order");
            for (int i = 0; i < report.size() - 1; i++) {
              if (Math.abs(report.get(i + 1) - report.get(i)) > 3) {
                System.out.println("UNSAFE Difference between adjacent numbers is greater than 3");
                safe = false;
                break;
              }
            }
            if (safe) {
              System.out.println("SAFE Difference between adjacent numbers is less than 3");
              result++;
              continue;
            }


          } else {
            System.out.println("UNSAFE Neither ascending nor descending order");
          }


          List<List<Integer>> newReports = new ArrayList<>();

            // for each unsafe report create a bunch of new reports with one element removed
            for (int i = 0; i < report.size(); i++) {
              List<Integer> temp = new ArrayList<>(report);
              temp.remove(i);
              if (!unsafeReports.contains(temp)) {
                newReports.add(temp);
              }
            }

          
          
          for (List<Integer> report2 : newReports) {
            safe = true;
            System.out.println(report2);
            // check if the numbers in report2 are in ascending order
            if (isAscending(report2)) {
              System.out.println("Ascending order");
              // check if the difference between adjacent numbers is less than 3
              for (int i = 0; i < report2.size() - 1; i++) {
                if (Math.abs(report2.get(i + 1) - report2.get(i)) > 3) {
                  System.out.println("UNSAFE Difference between adjacent numbers is greater than 3");
//                  unsafeReports.add(report2);
                  safe = false;
                  break;
                }
              }
              if (safe) {
                System.out.println("SAFE Difference between adjacent numbers is less than 3");
                result++;
                break;
              }
            } else if (isDescending(report2)) {
              System.out.println("Descending order");
              for (int i = 0; i < report2.size() - 1; i++) {
                if (Math.abs(report2.get(i + 1) - report2.get(i)) > 3) {
                  System.out.println("UNSAFE Difference between adjacent numbers is greater than 3");
                  safe = false;
                  break;
                }
              }
              if (safe) {
                System.out.println("SAFE Difference between adjacent numbers is less than 3");
                result++;
                break;
              }


            } else {
              System.out.println("UNSAFE Neither ascending nor descending order");
            }
          }
          


        }
      }


      System.out.println("Final Result: " + result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean isAscending(List<Integer> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) >= list.get(i + 1)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isDescending(List<Integer> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) <= list.get(i + 1)) {
        return false;
      }
    }
    return true;
  }

}


