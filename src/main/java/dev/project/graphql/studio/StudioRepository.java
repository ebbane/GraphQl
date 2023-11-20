package dev.project.graphql.studio;

import dev.project.graphql.studio.model.StudioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudioRepository extends CrudRepository<StudioEntity, Long>,
    PagingAndSortingRepository<StudioEntity, Long> {

}
