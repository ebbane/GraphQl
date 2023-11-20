package dev.project.graphql.editor.model;

import dev.project.graphql.game.model.Game;
import java.util.List;

public record Editor(
    String id,
    String name,
    List<Game> games
) {

}
