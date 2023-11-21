package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
import dev.project.graphql.studio.model.Studios;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StudioController {

  private final StudioService studioService;

  public StudioController(StudioService studioService) {
    this.studioService = studioService;
  }

  @QueryMapping
  public Studio studio(@Argument Long id) {
    return studioService.getStudioById(id);
  }

  @QueryMapping
  public Studios studios(@Argument Integer page) {
    Pageable pageable = PageRequest.of(Objects.requireNonNullElse(page, 0), 20);
    return studioService.getStudios(pageable);
  }
}
