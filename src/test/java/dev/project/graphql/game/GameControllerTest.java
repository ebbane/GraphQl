package dev.project.graphql.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.game.model.SearchGame;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(InstancioExtension.class)
class GameControllerTest {

  private GameController gameController;
  private GameService gameService;

  @BeforeEach
  void beforeEach() {
    gameService = Mockito.mock(GameService.class);
    gameController = new GameController(gameService);
  }

  @Test
  void game_nominal_expectedContent() {
    // Given
    Game game = Instancio.create(Game.class);
    when(gameService.getGameById(1L)).thenReturn(game);

    // When
    var actual = gameController.game(1L);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(game);
  }

  @Test
  void games_withoutParams_expectedContent() {
    // Given
    Games games = Instancio.create(Games.class);
    when(gameService.getGames(any(), any())).thenReturn(games);

    // When
    var actual = gameController.games(null, null, null, null);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(games);
  }

  @Test
  void games_withSearchParams_expectedContent() {
    // Given
    Games games = Instancio.create(Games.class);
    SearchGame searchGame = Instancio.create(SearchGame.class);
    when(gameService.getGames(eq(searchGame), any())).thenReturn(games);

    // When
    var actual = gameController.games(null, searchGame.genre(), searchGame.platform(),
        searchGame.studio());

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(games);
  }

  @Test
  void deleteGame_nominal_expectedContent() {
    // Given
    var gameId = 1L;
    when(gameService.deleteGameById(gameId)).thenReturn(true);

    // When
    var actual = gameController.deleteGame(gameId);

    // Then
    assertThat(actual).isTrue();
  }

}