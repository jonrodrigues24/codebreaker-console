package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Game;
import java.io.IOException;
import retrofit2.Response;

public class GameRepository {

  private final CodebreakerServiceProxy proxy;

  /**
   * Performs operation on instances of the model classes {@Link Game} and {@Link
   * edu.cnm.deepdive.model.Guess}, including sending stub instances to the web service, and receive
   * completed instances
   */
  public GameRepository() {
    proxy =  CodebreakerServiceProxy.getInstance();
  }

  /**
   * creates a stub (incomplete) instance of {@link Game}, setting the fields according to {@code pool}
   * and {@code length} ........ web service.
   * @param pool
   * @param length
   * @return
   * @throws IOException
   */
  public Game newGame(String pool, int length) throws IOException {

    Game gameStub = new Game();

    gameStub.setPool(pool);
    gameStub.setLength(length);
    //Uses a Retrofit Call object to execute the HTTP request and obtain the response.
    Response<Game> response = proxy.startGame(gameStub).execute();
    if (!response.isSuccessful()) {
      throw new IllegalArgumentException();
    }
    return response.body();
  }

  // TODO Define methods for obtaining a single existing game, the list of guesses in a game, and
  // submitting a new guess.
}
