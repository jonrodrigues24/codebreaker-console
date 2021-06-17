package edu.cnm.deepdive;

import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.service.GameRepository;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Implements a simple client for a Codebreaker (similar to MasterMind, Bulls and Cows) game
 * that runs as a web service. This uses console interaction (standard input &amp; output) to obtain
 * guesses from the user and display the result of each guess.
 *
 * @author Jonathan Rodrigues and DDC Java + Android Bootcamp cohort 13.
 */
public class Main {

  /**
   * Entry point for game. Connects to Codebreaker service to start each game, and query the user
   * for guesses, until the user declines to play again.
   * @param args Command line argusments (not currently used).
   * @throws IOException If a network or network resource (such as web service) fails in sending the
   * request and receiving the response.
   */
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    ResourceBundle bundle = ResourceBundle.getBundle("strings");
    GameRepository repository = new GameRepository();
    // TODO Create proxy class for service.
    do {




      // TODO Play a single game
      repository.newGame("0123456789", 3);
    } while (queryReplay(scanner, bundle));
  }

  /**
   * Prompts the user to play again. If a resource
   * @param scanner
   * @param bundle
   * @return
   */
  private static boolean queryReplay(Scanner scanner, ResourceBundle bundle) {

    System.out.println(bundle.getString("replay_prompt"));

    String input = scanner.nextLine().trim().toLowerCase();

    return (input.isEmpty() || input.charAt(0) != 'n');

  }

}
