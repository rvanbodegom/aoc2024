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
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          long firstNumber = Long.parseLong(line.split(": ")[0]);
          String secondNumber = line.split(": ")[1];
          List<Long> numbers = Arrays.stream(secondNumber.split(" ")).map(Long::parseLong).toList();
          //evaluate the numbers left to right
          int possibleOperators = numbers.size() - 1;
          // each operator can be either + or * for each possible operator,
          for (int i = 0; i < Math.pow(2, possibleOperators); i++) {
            String binary = Integer.toBinaryString(i);
            // pad the binary with leading 0's
            while (binary.length() < possibleOperators) {
              binary = "0" + binary;
            }


            long intermediateResult = numbers.get(0);
            for (int j = 0; j < binary.length(); j++) {
              char c = binary.charAt(j);
              if (c == '0') { // +
                intermediateResult += numbers.get(j + 1);
              } else { // *
                intermediateResult *= numbers.get(j + 1);
              }
              System.out.println("Intermediate Result: " + intermediateResult);
            }
            if (intermediateResult == firstNumber) {
              result += firstNumber;
              System.out.println("SUCCESS! results is now " + result);
              break; // if we found a result we can stop evaluating the rest of the binary strings
            }

            System.out.println("Binary: " + binary);
            System.out.println("=====================================");
          }

        }
      }

      System.out.println("Final Result: " + result);


    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


