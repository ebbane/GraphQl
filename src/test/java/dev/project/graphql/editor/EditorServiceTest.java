package dev.project.graphql.editor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.EditorEntity;
import java.util.Optional;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class, InstancioExtension.class})
class EditorServiceTest {

  @Mock
  private EditorRepository editorRepository;
  @Mock
  private EditorMapper editorMapper;
  private EditorService editorService;

  @BeforeEach
  void beforeEach() {
    editorService = new EditorService(editorRepository, editorMapper);
  }

  @Test
  void getEditorById_nominal_expectedObject() {
    // Given
    var editorId = 1L;
    EditorEntity editorEntity = Instancio.create(EditorEntity.class);
    when(editorRepository.findById(editorId)).thenReturn(Optional.of(editorEntity));
    Editor editor = Instancio.create(Editor.class);
    when(editorMapper.entityToEditor(editorEntity)).thenReturn(editor);

    // When
    var actual = editorService.getEditorById(editorId);

    // Then
    verify(editorMapper).entityToEditor(editorEntity);
    verify(editorRepository).findById(editorId);
    assertThat(actual).isEqualTo(editor);
  }

  @Test
  void deleteEditorById_nominal_true() {
    // Given
    var editorId = 1L;
    doNothing().when(editorRepository).deleteById(editorId);

    // When
    var actual = editorService.deleteEditorById(editorId);

    // Then
    assertThat(actual).isTrue();
  }

}