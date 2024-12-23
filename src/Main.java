import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
  static long result = 0;
  static boolean[][][] visitedPosition = new boolean[1000][1000][4];
  static long nrOfPossibleLoops = 0;

  public static void main(String[] args) {
    String filePath = "src/input.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      int positionRow = 0;
      int positionCol = 0;
      int startRow = 0;
      int startCol = 0;
      int row = 0;
      List<List<Character>> grid = new ArrayList<>();
      List<List<Character>> startGrid = new ArrayList<>();
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          grid.add(line.chars().mapToObj(e -> (char) e).collect(Collectors.toList()));
          if (line.contains("^")) {
            positionRow = row;
            positionCol = line.indexOf("^");
            startRow = row;
            startCol = line.indexOf("^");
          }
          row++;
        }
      }
      startGrid = grid.stream().map(ArrayList::new).collect(Collectors.toList());
      int direction = 0; // 0 = up, 1 = right, 2 = down, 3 = left
      boolean endIsReached = false;
      // first position should always become 'X'
//      grid.get(positionRow).set(positionCol, 'X'); // mark as visited
//      visitedPosition[positionRow][positionCol][0] = true;
      result++;

          for (int firstIndex = 0; firstIndex < grid.get(0).size(); firstIndex++) {

          System.out.println("Starting at: " + firstIndex);
            for (int secondIndex = 0; secondIndex < grid.size(); secondIndex++) {
//          System.out.println("Position Row si: " + secondIndex + " Position Col fi: " + firstIndex);
          // set this field to 'X'
          if (grid.get(secondIndex).get(firstIndex) == '.' && !(secondIndex == 5 && firstIndex == 4)) {
            grid.get(secondIndex).set(firstIndex, '#');
            do {
              if (direction == 0) {
                if (visitedPosition[positionRow][positionCol][direction]) {
                  nrOfPossibleLoops++;
                  System.out.println("loop detected by adding block at: " + secondIndex + " " + firstIndex);
                  break;
                }
                // go up until # or top edge of grid
                while (positionRow > 0) { // can go up
                  // && grid.get(positionRow - 1).get(positionCol) != '#'
                  if (grid.get(positionRow).get(positionCol) == '.') {
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][0] = true;
                    result++;
                  }
                  positionRow--;
                  // result++;
                  if (positionRow == 0) {
                    endIsReached = true;
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][0] = true;
                    result++;
                    break;
                  }
                  if (grid.get(positionRow - 1).get(positionCol) == '#') {
                    direction = 1;
                    break;
                  }
                }

//                if (grid.get(positionRow - 1).get(positionCol) == '#') {
//                  direction = 1;
//                }
//                System.out.println("END OF UP: Position Row: " + positionRow + " Position Col: " + positionCol +
//                    " current result: " + result + " secondIndex: " + secondIndex + " firstIndex: " + firstIndex + " nrOfPossibleLoops: " + nrOfPossibleLoops);
              }
              if (direction == 1) {
                if (visitedPosition[positionRow][positionCol][direction]) {

                  nrOfPossibleLoops++;
                  System.out.println("loop detected by adding block at: " + secondIndex + " " + firstIndex);
                  break;
                }
                // go right until # or right edge of grid
                while (positionCol < grid.get(0).size() - 1 ) { // can go right
                  //&& grid.get(positionRow).get(positionCol + 1) != '#'
                  if (grid.get(positionRow).get(positionCol) == '.') {
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][1] = true;
                    result++;
                  }
                  positionCol++;
                  // result++;
                  if (positionCol == grid.get(0).size() - 1) {
                    endIsReached = true;
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][1] = true;
                    result++;
                    break;
                  }
                  if (grid.get(positionRow).get(positionCol + 1) == '#') {
                    direction = 2;
                    break;
                  }
                }
//                System.out.println("END OF RIGHT: Position Row: " + positionRow + " Position Col: " + positionCol + " current result: " + result);
              }
              if (direction == 2) {
                if (visitedPosition[positionRow][positionCol][direction]) {

                  nrOfPossibleLoops++;
                  System.out.println("loop detected by adding block at: " + secondIndex + " " + firstIndex);
                  break;
                }
                // go down until # or bottom edge of grid
                while (positionRow < grid.size() - 1) { // && grid.get(positionRow + 1).get(positionCol) != '#') { // can go down
                  if (grid.get(positionRow).get(positionCol) == '.') {
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][2] = true;
                    result++;
                  }
                  positionRow++;
                  // result++;
                  if (positionRow == grid.size() - 1) {
                    endIsReached = true;
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][2] = true;
                    result++;
                    break;
                  }
                  if (grid.get(positionRow + 1).get(positionCol) == '#') {
                    direction = 3;
                    break;
                  }
                }
//                System.out.println("END OF DOWN: Position Row: " + positionRow + " Position Col: " + positionCol + " current result: " + result);
              }
              if (direction == 3) {

                if (visitedPosition[positionRow][positionCol][direction]) {

                  nrOfPossibleLoops++;
                  System.out.println("loop detected by adding block at: " + secondIndex + " " + firstIndex);
                  break;
                }

                // go left until # or left edge of grid
                while (positionCol > 0) { // && grid.get(positionRow).get(positionCol - 1) != '#') { // can go left
                  if (grid.get(positionRow).get(positionCol) == '.') {
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][3] = true;
                    result++;
                  }
                  positionCol--;
                  // result++;
                  if (positionCol == 0) {
                    endIsReached = true;
                    grid.get(positionRow).set(positionCol, 'X'); // mark as visited
                    visitedPosition[positionRow][positionCol][3] = true;
                    result++;
                    break;
                  }
                  if (grid.get(positionRow).get(positionCol - 1) == '#') {
                    direction = 0;
                    break;
                  }
                }
//                System.out.println("END OF LEFT: Position Row: " + positionRow + " Position Col: " + positionCol + " current result: " + result);
              }

            } while (!endIsReached);
//            for (List<Character> row2 : grid) {
//              for (Character c : row2) {
//                System.out.print(c);
//              }
//              System.out.println();
//            }
            endIsReached = false; // reset
            direction = 0; // reset
            grid.get(secondIndex).set(firstIndex, '.');
            positionRow = startRow;
            positionCol = startCol;
            visitedPosition = new boolean[1000][1000][4];
            grid = startGrid.stream().map(ArrayList::new).collect(Collectors.toList());


          }
        }
      }

      //positionRow != 0 || positionCol != 0 ||  positionCol != grid.get(0).size() - 1 || positionRow != grid.size() - 1);

//      for (List<Character> row2 : grid) {
//        for (Character c : row2) {
//          System.out.print(c);
//        }
//        System.out.println();
////        System.out.println(row2);
//      }
      //System.out.println("Grid: " + grid);
      System.out.println("FINAL Position Row: " + positionRow + " Position Col: " + positionCol);
      System.out.println("Final Result: " + result);
      System.out.println("Nr of possible loops: " + nrOfPossibleLoops);

    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }

}


