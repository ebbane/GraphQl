package dev.project.graphql.game.model;

import dev.project.graphql.common.EditorLight;
import dev.project.graphql.common.StudioLight;
import java.util.List;

public record Game(
    Long id,
    String name,
    List<String> genres,
    String publicationDate,
    List<EditorLight> editors,
    List<StudioLight> studios,
    List<String> platforms
) {

}
