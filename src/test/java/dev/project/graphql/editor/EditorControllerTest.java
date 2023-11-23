package dev.project.graphql.editor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.Editors;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(InstancioExtension.class)
class EditorControllerTest {

  private EditorController editorController;
  private EditorService editorService;

  @BeforeEach
  void beforeEach() {
    editorService = Mockito.mock(EditorService.class);
    editorController = new EditorController(editorService);
  }

  @Test
  void editor_nominal_expectedContent() {
    // Given
    Editor editor = Instancio.create(Editor.class);
    when(editorService.getEditorById(1L)).thenReturn(editor);

    // When
    var actual = editorController.editor(1L);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(editor);
  }

  @Test
  void editors_withoutPage_expectedContent() {
    // Given
    Editors editors = Instancio.create(Editors.class);
    when(editorService.getEditors(any())).thenReturn(editors);

    // When
    var actual = editorController.editors(null);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(editors);
  }

  @Test
  void deleteEditor_nominal_expectedContent() {
    // Given
    var editorId = 1L;
    when(editorService.deleteEditorById(editorId)).thenReturn(true);

    // When
    var actual = editorController.deleteEditor(editorId);

    // Then
    assertThat(actual).isTrue();
  }

  @Test
  void createEditor_nominal_expectedContent() {
    // Given
    var editorName = "Sega";
    when(editorService.createEditor(editorName)).thenReturn(1L);

    // When
    var actual = editorController.createEditor(editorName);

    // Then
    assertThat(actual).isEqualTo(1L);
  }

}