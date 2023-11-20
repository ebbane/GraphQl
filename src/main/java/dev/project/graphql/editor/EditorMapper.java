package dev.project.graphql.editor;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.EditorEntity;
import dev.project.graphql.editor.model.Editors;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EditorMapper {

  Editor entityToEditor(EditorEntity entity);


  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPage")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPrevious")
  @Mapping(target = "results", source = "content")
  Editors mapPageToEditors(Page<EditorEntity> page);

  @Named("getNextPage")
  default Integer getNextPage(Pageable page) {
    return page.getPageNumber() + 1;
  }

  @Named("getPrevious")
  default Integer getPrevious(Pageable page) {
    return (page.getPageNumber() == 0) ? 0 : (page.getPageNumber() - 1);
  }


}
