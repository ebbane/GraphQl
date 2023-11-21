
package dev.project.graphql.game;

import dev.project.graphql.editor.EditorMapper;
import dev.project.graphql.game.model.Game;
import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.Games;
import dev.project.graphql.studio.StudioMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = {EditorMapper.class, StudioMapper.class})
public interface GameMapper {

  Game entityToGame(GameEntity gameEntity);

  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPageGame")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPreviousGame")
  @Mapping(target = "results", source = "content")
  Games mapPageToGames(Page<GameEntity> page);

  @Named("getNextPageGame")
  default Integer getNextPageGame(Pageable page) {
    return page.getPageNumber() + 1;
  }

  @Named("getPreviousGame")
  default Integer getPreviousGame(Pageable page) {
    return (page.getPageNumber() == 0) ? 0 : (page.getPageNumber() - 1);
  }


}
