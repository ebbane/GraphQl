package dev.project.graphql.studio.model;

import dev.project.graphql.common.Infos;
import java.util.List;

public record Studios(
    Infos infos,
    List<Studio> results
) {

}
