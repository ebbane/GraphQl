package dev.project.graphql.editor.model;

import dev.project.graphql.common.GameLight;
import java.util.List;

public record Editor(
    String id,
    String name,
    List<GameLight> games
) {

}
