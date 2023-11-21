package dev.project.graphql.editor;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.EditorEntity;
import dev.project.graphql.editor.model.Editors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EditorService {

  private final EditorRepository editorRepository;
  private final EditorMapper editorMapper;

  public EditorService(EditorRepository editorRepository, EditorMapper editorMapper) {
    this.editorRepository = editorRepository;
    this.editorMapper = editorMapper;
  }

  public Editor getEditorById(Long id) {
    return editorRepository.findById(id)
        .map(editorMapper::entityToEditor)
        .orElse(null);
  }

  public Editors getEditors(Pageable pageable) {
    Page<EditorEntity> editors = editorRepository.findAll(pageable);
    return editorMapper.mapPageToEditors(editors);
  }

  public Boolean deleteEditorById(Long id) {
    try {
      editorRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
