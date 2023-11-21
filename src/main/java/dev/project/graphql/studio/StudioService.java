package dev.project.graphql.studio;

import dev.project.graphql.studio.model.Studio;
import dev.project.graphql.studio.model.StudioEntity;
import dev.project.graphql.studio.model.Studios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  public Studios getStudios(Pageable pageable) {
    Page<StudioEntity> studios = studioRepository.findAll(pageable);
    return studioMapper.mapPageToStudios(studios);
  }

  public Boolean deleteStudioById(Long id) {
    try {
      studioRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
