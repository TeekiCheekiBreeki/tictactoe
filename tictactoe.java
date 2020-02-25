import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
//make new game board
      char[][] gameBoard ={{' ','|',' ','|',' '},
                          {'-','+','-','+','-'},
                          {' ','|',' ','|',' '},
                          {'-','+','-','+','-'},
                          {' ','|',' ','|',' '}};

    printGameBoard(gameBoard);//print current status of board

//loop, so that game continues(not just 1 input)
    while(true){
      Scanner scan = new Scanner(System.in);
      System.out.println("Enter your placement (1-9)");
      int playerPosition = scan.nextInt();
//check if position is already taken, so it doesnt overwrite it
      while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPositions)){
        System.out.println("Position taken! Enter valid position");
        playerPosition = scan.nextInt();
      }
//places piece on board
      placePiece(gameBoard, playerPosition, "player");
      String result = checkWinner();
      if(result.length()>0){
      System.out.println(result);
      break;
      }

//cpu, same while loop to check if position is taken, and then method placePiece to place the piece
      Random random = new Random();
      int cpuPos = random.nextInt(9) + 1;

      while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
        cpuPos = random.nextInt(9) + 1;
      }
      placePiece(gameBoard, cpuPos, "cpu");
//update the board
      printGameBoard(gameBoard);
//after updating board, check if there is any winner. If theres a winner, calls method checkWinner and ends the game
      result = checkWinner();
        if(result.length()>0){
        System.out.println(result);
        break;
    }
  }


  }
//method to print the board
    public static void printGameBoard(char[][] gameBoard) {
      for(char[] row : gameBoard){
        for(char c : row){
          System.out.print(c);
        }
        System.out.println();
      }
    }
    //method to place the piece on position. We divide it on 2 users, cpu and player(prints x if player and o if cpu). then go to switch
    public static void placePiece(char[][] gameBoard, int position, String user){
      char symbol = ' ';
      if(user.equals("player")){
        symbol = 'x';
        playerPositions.add(position);
      }else if(user.equals("cpu")){
        symbol = 'o';
        cpuPositions.add(position);
      }
      switch(position){
        case 1:
          gameBoard[0][0] = symbol;
          break;
        case 2:
          gameBoard[0][2] = symbol;
          break;
        case 3:
          gameBoard[0][4] = symbol;
          break;
        case 4:
          gameBoard[2][0] = symbol;
          break;
        case 5:
          gameBoard[2][2] = symbol;
          break;
        case 6:
          gameBoard[2][4] = symbol;
          break;
        case 7:
          gameBoard[4][0] = symbol;
          break;
        case 8:
          gameBoard[4][2] = symbol;
          break;
        case 9:
          gameBoard[4][4] = symbol;
          break;
        default:
          break;
      }
    }
    //method to see who wins. We need winning conditions, which are if u have 3 in row, 3 diagonal or 3 in column. Make another list of lists and add all lists to
    //then check if player has win condition or not
    public static String checkWinner(){
      List topRow = Arrays.asList(1, 2, 3);
      List midRow = Arrays.asList(4, 5, 6);
      List botRow = Arrays.asList(7, 8, 9);
      List leftCol = Arrays.asList(1, 4, 7);
      List midCol = Arrays.asList(2, 5, 8);
      List rightCol = Arrays.asList(3, 6, 9);
      List cross1 = Arrays.asList(1, 5, 9);
      List cross2 = Arrays.asList(7, 5, 3);

      List<List> winning = new ArrayList<List>();
      winning.add(topRow);
      winning.add(midRow);
      winning.add(botRow);
      winning.add(leftCol);
      winning.add(midCol);
      winning.add(rightCol);
      winning.add(cross1);
      winning.add(cross2);
      for(List l : winning){
        if(playerPositions.containsAll(l)){
          return "Congratz, u won";

        }else if(cpuPositions.containsAll(l)){
          return "booo u suck";
        }else if(playerPositions.size() + cpuPositions.size() == 9){
          return "tie";
        }
        }
        return "";
      }
    }
