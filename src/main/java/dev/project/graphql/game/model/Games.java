package dev.project.graphql.game.model;

import dev.project.graphql.common.Infos;
import java.util.List;

public record Games(
    Infos infos,
    List<Game> results
) {

}
