package dev.project.graphql.editor;

import static org.assertj.core.api.Assertions.assertThat;

import dev.project.graphql.editor.model.EditorEntity;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class EditorMapperTest {

  private EditorMapper editorMapper;

  @BeforeEach
  void beforeEach() {
    editorMapper = new EditorMapperImpl();
  }

  @Test
  void entityToEditor_nominal_expectedObject() {
    // Given
    EditorEntity given = Instancio.create(EditorEntity.class);

    // When
    var actual = editorMapper.entityToEditor(given);

    // Then
    assertThat(actual)
        .isNotNull()
        .satisfies(res -> {
          assertThat(res.id()).isEqualTo(given.getId());
          assertThat(res.name()).isEqualTo(given.getName());
          assertThat(res.games()).anySatisfy(gameLight -> assertThat(given.getGames())
              .anySatisfy(gameEntity -> {
                assertThat(gameEntity.getId()).isEqualTo(gameLight.id());
                assertThat(gameEntity.getName()).isEqualTo(gameLight.name());
                assertThat(gameEntity.getGenres()).isEqualTo(gameLight.genres());
                assertThat(gameEntity.getPlatforms()).isEqualTo(gameLight.platforms());
                assertThat(gameEntity.getPublicationDate()).isEqualTo(gameLight.publicationDate());
              }));
        });
  }

  @Test
  void mapPageToEditors_nominal_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(0, 20);
    List<EditorEntity> editors = Instancio.createList(EditorEntity.class);
    Page<EditorEntity> given = new PageImpl<>(editors, pageable, editors.size());

    // When
    var actual = editorMapper.mapPageToEditors(given);

    // Then
    assertThat(actual).isNotNull()
        .satisfies(res -> {
          assertThat(res.infos().count()).isEqualTo(given.getTotalElements());
          assertThat(res.infos().pages()).isEqualTo(given.getTotalPages());
          assertThat(res.results()).anySatisfy(content -> assertThat(editors)
              .anySatisfy(editor -> {
                assertThat(editor.getId()).isEqualTo(content.id());
                assertThat(editor.getName()).isEqualTo(content.name());
              }));
        });
  }
}