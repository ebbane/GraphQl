package dev.project.graphql.editor;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.Editors;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EditorController {

  private final EditorService editorService;

  public EditorController(EditorService editorService) {
    this.editorService = editorService;
  }

  @QueryMapping
  public Editor editor(@Argument Long id) {
    return editorService.getEditorById(id);
  }

  @QueryMapping
  public Editors editors(@Argument Integer page) {
    Pageable pageable = PageRequest.of(Objects.requireNonNullElse(page, 0), 20);
    return editorService.getEditors(pageable);
  }
}
