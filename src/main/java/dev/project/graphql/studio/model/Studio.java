package dev.project.graphql.studio.model;

import dev.project.graphql.common.GameLight;
import java.util.List;

public record Studio(
    Long id,
    String name,
    List<GameLight> games
) {

}
