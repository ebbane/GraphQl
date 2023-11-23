package dev.project.graphql.common;

import java.time.LocalDateTime;
import java.util.List;

public record GameLight(
    Long id,
    String name,
    List<String> genres,
    LocalDateTime publicationDate,
    List<EditorLight> editors,
    List<StudioLight> studios,
    List<String> platforms

) {

}
