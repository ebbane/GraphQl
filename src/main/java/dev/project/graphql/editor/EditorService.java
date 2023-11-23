package dev.project.graphql.editor;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.editor.model.EditorEntity;
import dev.project.graphql.editor.model.Editors;
import java.util.List;
import java.util.stream.StreamSupport;
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

  public Long createEditor(String name) {
    var editorToSave = new EditorEntity();
    editorToSave.setName(name);
    return editorRepository.save(editorToSave).getId();
  }

  public List<EditorEntity> findAllEditorsByIds(List<Long> editorIds) {
    var editorEntities = editorRepository.findAllById(editorIds);
    return StreamSupport.stream(editorEntities.spliterator(), false)
        .toList();
  }

}
