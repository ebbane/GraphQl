package dev.project.graphql.common;

public record Infos(
    int count,
    int pages,
    Integer nextPage,
    Integer previousPage
) {

}
