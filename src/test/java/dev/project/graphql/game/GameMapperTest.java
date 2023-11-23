package dev.project.graphql.game;

import static org.assertj.core.api.Assertions.assertThat;

import dev.project.graphql.game.model.GameEntity;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class GameMapperTest {

  private GameMapper gameMapper;

  @BeforeEach
  void beforeEach() {
    gameMapper = new GameMapperImpl();
  }

  @Test
  void entityToGame_nominal_expectedObject() {
    // Given
    GameEntity given = Instancio.create(GameEntity.class);

    // When
    var actual = gameMapper.entityToGame(given);

    // Then
    assertThat(actual)
        .isNotNull()
        .satisfies(res -> {
          assertThat(res.id()).isEqualTo(given.getId());
          assertThat(res.name()).isEqualTo(given.getName());
          assertThat(res.publicationDate()).isEqualTo(given.getPublicationDate());
          assertThat(res.genres()).isEqualTo(given.getGenres());
          assertThat(res.platforms()).isEqualTo(given.getPlatforms());
          assertThat(res.editors()).anySatisfy(editorLight -> assertThat(given.getEditors())
              .anySatisfy(editorEntity -> {
                assertThat(editorEntity.getId()).isEqualTo(editorLight.id());
                assertThat(editorEntity.getName()).isEqualTo(editorLight.name());
              }));
          assertThat(res.studios()).anySatisfy(studioLight -> assertThat(given.getStudios())
              .anySatisfy(studioEntity -> {
                assertThat(studioEntity.getId()).isEqualTo(studioLight.id());
                assertThat(studioEntity.getName()).isEqualTo(studioLight.name());
              }));
        });
  }

  @Test
  void mapPageToGames_nominal_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(0, 20);
    List<GameEntity> games = Instancio.createList(GameEntity.class);
    Page<GameEntity> given = new PageImpl<>(games, pageable, games.size());

    // When
    var actual = gameMapper.mapPageToGames(given);

    // Then
    assertThat(actual).isNotNull()
        .satisfies(res -> {
          assertThat(res.infos().count()).isEqualTo(given.getTotalElements());
          assertThat(res.infos().pages()).isEqualTo(given.getTotalPages());
          assertThat(res.infos().previousPage()).isNotNull();
          assertThat(res.infos().nextPage()).isNotNull();
          assertThat(res.infos().pages()).isEqualTo(given.getTotalPages());
          assertThat(res.results()).anySatisfy(content -> assertThat(games)
              .anySatisfy(game -> {
                assertThat(game.getId()).isEqualTo(content.id());
                assertThat(game.getName()).isEqualTo(content.name());
                assertThat(game.getPublicationDate()).isEqualTo(content.publicationDate());
                assertThat(game.getGenres()).isEqualTo(content.genres());
                assertThat(game.getPlatforms()).isEqualTo(content.platforms());
              }));
        });
  }
}