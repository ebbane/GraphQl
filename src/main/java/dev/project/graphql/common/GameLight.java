package dev.project.graphql.common;

import java.util.List;

public record GameLight(
    Long id,
    String name,
    List<String> genres,
    String publicationDate,
    List<EditorLight> editors,
    List<StudioLight> studios,
    List<String> platforms

) {

}
