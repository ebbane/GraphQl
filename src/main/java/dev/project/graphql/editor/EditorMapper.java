package dev.project.graphql.editor;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.EditorEntity;
import dev.project.graphql.editor.model.Editors;
import dev.project.graphql.utils.PaginMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EditorMapper extends PaginMapper {

  Editor entityToEditor(EditorEntity entity);


  @Mapping(source = "totalElements", target = "infos.count")
  @Mapping(source = "totalPages", target = "infos.pages")
  @Mapping(source = "pageable", target = "infos.nextPage", qualifiedByName = "getNextPage")
  @Mapping(source = "pageable", target = "infos.previousPage", qualifiedByName = "getPrevious")
  @Mapping(target = "results", source = "content")
  Editors mapPageToEditors(Page<EditorEntity> page);

}
