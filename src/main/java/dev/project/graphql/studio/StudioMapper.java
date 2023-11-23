package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
import dev.project.graphql.studio.model.StudioEntity;
import dev.project.graphql.studio.model.Studios;
import dev.project.graphql.utils.PaginMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudioMapper extends PaginMapper {

  Studio entityToStudio(StudioEntity entity);

  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPage")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPrevious")
  @Mapping(target = "results", source = "content")
  Studios mapPageToStudios(Page<StudioEntity> page);

}
