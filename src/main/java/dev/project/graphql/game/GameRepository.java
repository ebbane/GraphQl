package dev.project.graphql.game;

import dev.project.graphql.game.model.GameEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long>, JpaSpecificationExecutor<GameEntity>{

}
