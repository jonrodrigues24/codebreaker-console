package edu.cnm.deepdive.service;

import edu.cnm.deepdive.model.Error;
import edu.cnm.deepdive.model.Game;
import edu.cnm.deepdive.model.Guess;
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
  public Game newGame(String pool, int length) throws IOException, IllegalArgumentException {

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

  public Guess newGuess(Game game, String text)
      throws IOException, ValidationException {

    Guess guess = new Guess();
    guess.setText(text);
    Response<Guess> response = proxy.submitGuess(game.getId(), guess).execute();
    if (!response.isSuccessful()) {
      //noinspection ConstantConditions
      Error error = CodebreakerServiceProxy.getGsonInstance()
          .fromJson(response.errorBody().string(), Error.class);
      throw new ValidationException(error);
    }
    return response.body();
  }

  public static class ValidationException extends IllegalArgumentException {

    private final Error error;

    public ValidationException(Error error) {
      this.error = error;
    }

    public Error getError() {
      return error;
    }
  }
}
