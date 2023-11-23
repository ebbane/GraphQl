package dev.project.graphql.studio;

import static org.assertj.core.api.Assertions.assertThat;

import dev.project.graphql.studio.model.StudioEntity;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class StudioMapperTest {

  private StudioMapper studioMapper;

  @BeforeEach
  void beforeEach() {
    studioMapper = new StudioMapperImpl();
  }

  @Test
  void entityToStudio_nominal_expectedObject() {
    // Given
    StudioEntity given = Instancio.create(StudioEntity.class);

    // When
    var actual = studioMapper.entityToStudio(given);

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
  void mapPageToStudios_nominal_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(0, 20);
    List<StudioEntity> studios = Instancio.createList(StudioEntity.class);
    Page<StudioEntity> given = new PageImpl<>(studios, pageable, studios.size());

    // When
    var actual = studioMapper.mapPageToStudios(given);

    // Then
    assertThat(actual).isNotNull()
        .satisfies(res -> {
          assertThat(res.infos().count()).isEqualTo(given.getTotalElements());
          assertThat(res.infos().pages()).isEqualTo(given.getTotalPages());
          assertThat(res.results()).anySatisfy(content -> assertThat(studios)
              .anySatisfy(studio -> {
                assertThat(studio.getId()).isEqualTo(content.id());
                assertThat(studio.getName()).isEqualTo(content.name());
              }));
        });
  }


}