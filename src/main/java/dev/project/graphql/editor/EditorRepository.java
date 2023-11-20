package dev.project.graphql.editor;

import dev.project.graphql.editor.model.EditorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorRepository extends PagingAndSortingRepository<EditorEntity, Long>, CrudRepository<EditorEntity, Long> {

}
