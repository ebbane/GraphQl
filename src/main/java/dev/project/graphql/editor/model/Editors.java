package dev.project.graphql.editor.model;

import dev.project.graphql.common.Infos;
import java.util.List;

public record Editors(
    Infos infos,
    List<Editor> results
) {

}
