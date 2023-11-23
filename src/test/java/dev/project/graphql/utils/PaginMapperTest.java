package dev.project.graphql.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class PaginMapperTest {

  private PaginMapper paginMapper;

  @BeforeEach
  void beforeEach() {
    paginMapper = new PaginMapperImpl();
  }

  @Test
  void getNextPage_nominal_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(1, 20);

    // When
    var actual = paginMapper.getNextPage(pageable);

    // Then
    assertThat(actual).isEqualTo(2);

  }

  @Test
  void getPrevious_firstPage_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(0, 20);

    // When
    var actual = paginMapper.getPrevious(pageable);

    // Then
    assertThat(actual).isZero();
  }

  @Test
  void getPrevious_secondPage_expectedObject() {
    // Given
    Pageable pageable = PageRequest.of(1, 20);

    // When
    var actual = paginMapper.getPrevious(pageable);

    // Then
    assertThat(actual).isZero();
  }

}