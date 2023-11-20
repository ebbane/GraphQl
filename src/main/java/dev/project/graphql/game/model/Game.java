package dev.project.graphql.game.model;

import dev.project.graphql.editor.model.Editor;
import dev.project.graphql.studio.model.Studio;
import java.util.List;

public record Game(
    Long id,
    String name,
    List<String> genres,
    Integer publicationDate,
    List<Editor> editors,
    List<Studio> studios,
    List<String> platforms
) {

}
