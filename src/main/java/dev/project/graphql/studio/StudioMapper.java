package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
import dev.project.graphql.studio.model.StudioEntity;
import dev.project.graphql.studio.model.Studios;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudioMapper {

  Studio entityToStudio(StudioEntity entity);


  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPage")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPrevious")
  @Mapping(target = "results", source = "content")
  Studios mapPageToStudios(Page<StudioEntity> page);

  @Named("getNextPage")
  default Integer getNextPage(Pageable page) {
    return page.getPageNumber() + 1;
  }

  @Named("getPrevious")
  default Integer getPrevious(Pageable page) {
    return (page.getPageNumber() == 0) ? 0 : (page.getPageNumber() - 1);
  }


}
