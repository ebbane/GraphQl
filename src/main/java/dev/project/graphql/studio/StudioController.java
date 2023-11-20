package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
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
}
