package dev.project.graphql.game.model;

import dev.project.graphql.editor.model.EditorEntity;
import dev.project.graphql.studio.model.StudioEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "game")
public class GameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "publication_date")
  private LocalDateTime publicationDate;

  @ManyToMany
  @JoinTable(
      name = "game_editor",
      joinColumns = @JoinColumn(name = "game_id"),
      inverseJoinColumns = @JoinColumn(name = "editor_id")
  )
  private List<EditorEntity> editors;

  @ManyToMany
  @JoinTable(
      name = "game_studio",
      joinColumns = @JoinColumn(name = "game_id"),
      inverseJoinColumns = @JoinColumn(name = "studio_id")
  )
  private List<StudioEntity> studios;

  @ElementCollection
  @CollectionTable(name = "game_genre", joinColumns = @JoinColumn(name = "game_id"))
  @Column(name = "genre")
  private List<String> genres;

  @ElementCollection
  @CollectionTable(name = "game_platform", joinColumns = @JoinColumn(name = "game_id"))
  @Column(name = "platform")
  private List<String> platforms;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
  }

  public LocalDateTime getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDateTime publicationDate) {
    this.publicationDate = publicationDate;
  }

  public List<EditorEntity> getEditors() {
    return editors;
  }

  public void setEditors(List<EditorEntity> editors) {
    this.editors = editors;
  }

  public List<StudioEntity> getStudios() {
    return studios;
  }

  public void setStudios(List<StudioEntity> studios) {
    this.studios = studios;
  }

  public List<String> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<String> platforms) {
    this.platforms = platforms;
  }
}