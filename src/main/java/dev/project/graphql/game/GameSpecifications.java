package dev.project.graphql.game;

import dev.project.graphql.game.model.GameEntity;
import dev.project.graphql.game.model.SearchGame;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class GameSpecifications {

  public static Specification<GameEntity> toPredicate(SearchGame searchRequest) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (StringUtils.isNotBlank(searchRequest.genre())) {
        predicates.add(criteriaBuilder.isMember(searchRequest.genre(), root.get("genres")));
      }

      if (StringUtils.isNotBlank(searchRequest.platform())) {
        predicates.add(criteriaBuilder.isMember(searchRequest.platform(),
            root.get("platforms")));
      }

      if (StringUtils.isNotBlank(searchRequest.studio())) {
        predicates.add(criteriaBuilder.isMember(searchRequest.studio(),
            root.get("studios").get("name")));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }


}
