package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

  private final StudioRepository studioRepository;
  private final StudioMapper studioMapper;

  public StudioService(StudioRepository studioRepository, StudioMapper studioMapper) {
    this.studioRepository = studioRepository;
    this.studioMapper = studioMapper;
  }

  public Studio getStudioById(Long id) {
    return studioRepository.findById(id)
        .map(studioMapper::entityToStudio)
        .orElse(null);
  }

}
