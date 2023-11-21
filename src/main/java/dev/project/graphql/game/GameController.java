package dev.project.graphql.game;

import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.game.model.SearchGame;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @QueryMapping
  public Games games(@Argument Integer page, @Argument String genre,
      @Argument String platform, @Argument String studio) {
    SearchGame search = new SearchGame(genre, platform, studio);
    Pageable pageable = PageRequest.of(Objects.requireNonNullElse(page, 0), 20);
    return gameService.getGames(search, pageable);
  }

  @QueryMapping
  public Game game(@Argument Long id) {
    return gameService.getGameById(id);
  }


  @MutationMapping
  public Boolean deleteGame(@Argument Long id) {
    return gameService.deleteGameById(id);
  }

  @MutationMapping
  public Long createGame(
      @Argument String name,
      @Argument List<String> genres,
      @Argument LocalDateTime publicationDate,
      @Argument List<Long> editorIds,
      @Argument List<Long> studioIds,
      @Argument List<String> platforms) {
    var gameToSave = new GameEntity(name, publicationDate, genres, platforms);
    return gameService.createGame(gameToSave, editorIds, studioIds);
  }

}
