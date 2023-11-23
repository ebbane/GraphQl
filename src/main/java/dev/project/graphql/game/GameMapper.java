
package dev.project.graphql.game;

import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.utils.PaginMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GameMapper extends PaginMapper {

  Game entityToGame(GameEntity gameEntity);

  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPage")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPrevious")
  @Mapping(target = "results", source = "content")
  Games mapPageToGames(Page<GameEntity> page);

}
