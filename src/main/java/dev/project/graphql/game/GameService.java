package dev.project.graphql.game;

import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.game.model.SearchGame;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GameMapper gameMapper;

  public GameService(GameRepository gameRepository, GameMapper gameMapper) {
    this.gameRepository = gameRepository;
    this.gameMapper = gameMapper;
  }


  public Game getGameById(Long id) {
    return gameRepository.findById(id)
        .map(gameMapper::entityToGame)
        .orElse(null);
  }

  public Games getGames(SearchGame searchGame, Pageable pageable) {
    var games = gameRepository.findAll(GameSpecifications.toPredicate(searchGame), pageable);
    return gameMapper.mapPageToGames(games);
  }

  public Boolean deleteGameById(Long id) {
    try {
      gameRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
