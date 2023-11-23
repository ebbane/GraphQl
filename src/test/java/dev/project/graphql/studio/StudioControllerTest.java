package dev.project.graphql.studio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import dev.project.graphql.studio.model.Studio;
import dev.project.graphql.studio.model.Studios;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

@ExtendWith(InstancioExtension.class)
class StudioControllerTest {


  private StudioController studioController;
  private StudioService studioService;

  @BeforeEach
  void beforeEach() {
    studioService = Mockito.mock(StudioService.class);
    studioController = new StudioController(studioService);
  }

  @Test
  void studio_nominal_expectedContent() {
    // Given
    Studio studio = Instancio.create(Studio.class);
    when(studioService.getStudioById(1L)).thenReturn(studio);

    // When
    var actual = studioController.studio(1L);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(studio);
  }

  @Test
  void studios_withoutPage_expectedContent() {
    // Given
    Studios studios = Instancio.create(Studios.class);
    when(studioService.getStudios(any())).thenReturn(studios);

    // When
    var actual = studioController.studios(null);

    // Then
    assertThat(actual).isNotNull()
        .isEqualTo(studios);
  }

  @Test
  void deleteStudio_nominal_expectedContent() {
    // Given
    var studioId = 1L;
    when(studioService.deleteStudioById(studioId)).thenReturn(true);

    // When
    var actual = studioController.deleteStudio(studioId);

    // Then
    assertThat(actual).isTrue();
  }

  @Test
  void createStudio_nominal_expectedContent() {
    // Given
    var studioName = "Acute Games";
    when(studioService.createStudio(studioName)).thenReturn(1L);

    // When
    var actual = studioController.createStudio(studioName);

    // Then
    assertThat(actual).isEqualTo(1L);
  }

}