package dev.project.graphql.utils;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.data.domain.Pageable;

@Mapper(componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PaginMapper {

  @Named("getNextPage")
  default Integer getNextPage(Pageable page) {
    return page.getPageNumber() + 1;
  }

  @Named("getPrevious")
  default Integer getPrevious(Pageable page) {
    return (page.getPageNumber() == 0) ? 0 : (page.getPageNumber() - 1);
  }
}