package dev.project.graphql.game;

import dev.project.graphql.editor.EditorService;
import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.game.model.SearchGame;
import dev.project.graphql.studio.StudioService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final EditorService editorService;
  private final StudioService studioService;
  private final GameMapper gameMapper;

  public GameService(GameRepository gameRepository, EditorService editorService,
      StudioService studioService, GameMapper gameMapper) {
    this.gameRepository = gameRepository;
    this.editorService = editorService;
    this.studioService = studioService;
    this.gameMapper = gameMapper;
  }

  public Game getGameById(Long id) {
    return gameRepository.findById(id)
        .map(gameMapper::entityToGame)
        .orElse(null);
  }

  public Games getGames(SearchGame searchGame, Pageable pageable) {
    Page<GameEntity> games = gameRepository.findAll(GameSpecifications.toPredicate(searchGame),
        pageable);
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

  public Long createGame(GameEntity gameToSave, List<Long> editorIds, List<Long> studioIds) {
    gameToSave.setEditors(editorService.findAllEditorsByIds(editorIds));
    gameToSave.setStudios(studioService.findAllStudiosByIds(studioIds));
    return gameRepository.save(gameToSave).getId();
  }

}
