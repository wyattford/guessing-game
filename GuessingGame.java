import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;


class GuessingGame {
  public static final Integer MIN = 0;
  public static final Integer MAX = 101;
  public static final String HAIKU = "I'm very tired\nI do not want to be here\nWhen does this class end?";

  public static void main(String[] args) {
    manageGame();

  }

  public static void manageGame() {
    Hashtable<Integer, Integer> games = new Hashtable<Integer, Integer>();
    Random randGen = new Random();
    Scanner myScanner = new Scanner(System.in);
    boolean playing = true;
    int gameNumber = 1;
    while(playing) {
      int moveCount = playGame(myScanner, randGen.nextInt(MAX));
      games.put(gameNumber, moveCount);
      gameNumber++;
      playing = playAgainQuestion(myScanner);
    }
    getStats(games);
  }

  public static void getStats(Hashtable games) {
    int totalGuesses = sumGameGuesses(games);
    System.out.println("Overall results:");
    System.out.printf("Total games   = %d%n", games.size());
    System.out.printf("Total guesses = %d%n", totalGuesses);
    System.out.println("Guesses/game  = " + df.format((double)totalGuesses/games.size(), 1));
    System.out.printf("Best game     = %d%n", findBestGame(games));
  }

  public static boolean playAgainQuestion(Scanner scn) {
    System.out.print("Do you want to play again? ");
    return scn.next().substring(0, 1).toLowerCase().equals("y");
  }

  public static int sumGameGuesses(Hashtable games) {
    int total = 0;
    for (int i = 1; i<games.size()+1; i++) {
      total += (int) games.get(i);
    }
    return total;
  }

  public static int findBestGame(Hashtable games) {
    int bestGame = 0;
    int bestGameScore = 100000;
    for (int i = 1; i<games.size()+1; i++) {
      if((int) games.get(i)<bestGameScore) {
        bestGame = i;
      }
    }
    return bestGame;
  }

  public static int playGame(Scanner scn, Integer answer) {
    int moveCount = 0;
    boolean playing = true;
    System.out.println(answer);
    System.out.printf("I'm thinking of a number between %s and %s...%n", MIN+1, MAX-1);
    while(playing) {
      System.out.print("Your guess? ");
      int userInput = scn.nextInt();
      moveCount++;
      if (userInput==answer) {
        System.out.println("Correct!");
        System.out.printf("You got it right in %s guesses!%n", moveCount);
        playing = false;
      } else {
        if(userInput<answer) {
          System.out.println("It's higher.");
        } else {
          System.out.println("It's lower.");
        }
      }
    }
    return moveCount;
  }
}
