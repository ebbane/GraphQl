package dev.project.graphql.studio.model;

import dev.project.graphql.game.model.Game;
import java.util.List;

public record Studio(
    String id,
    String name,
    List<Game> games
) {

}
