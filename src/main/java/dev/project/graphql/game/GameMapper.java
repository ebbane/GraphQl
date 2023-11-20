
package dev.project.graphql.game;

import dev.project.graphql.editor.EditorMapper;
import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.studio.StudioMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = {EditorMapper.class, StudioMapper.class})
public interface GameMapper {


  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(target = "results", source = "content")
  Games mapPageToGames(Page<GameEntity> page);



  Game entityToGame(GameEntity gameEntity);


}
