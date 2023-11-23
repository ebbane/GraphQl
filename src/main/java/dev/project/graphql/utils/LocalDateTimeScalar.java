package dev.project.graphql.utils;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeScalar {

  private static final String DATE_PATTERN = "yyyy-MM-dd";

  public static final GraphQLScalarType LOCAL_DATE_TIME = GraphQLScalarType.newScalar()
      .name("LocalDateTime")
      .description("A custom scalar that handles LocalDateTime")
      .coercing(new Coercing<LocalDateTime, String>() {
        @Override
        public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
          return serializeLocalDateTime(dataFetcherResult);
        }

        @Override
        public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
          return parseLocalDateTimeFromVariable(input);
        }

        @Override
        public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
          return parseLocalDateTimeFromAstLiteral(input);
        }
      })
      .build();

  private static boolean looksLikeLocalDateTime(String possibleDateTimeValue) {
    return Pattern.matches(DATE_PATTERN, possibleDateTimeValue);
  }

  private static String serializeLocalDateTime(Object dataFetcherResult) {
    if (dataFetcherResult instanceof LocalDateTime) {
      return ((LocalDateTime) dataFetcherResult).format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    } else {
      throw new CoercingSerializeException(
          "Unable to serialize " + dataFetcherResult + " as LocalDateTime");
    }
  }

  private static LocalDateTime parseLocalDateTimeFromVariable(Object input) {
    if (input instanceof String) {
      String possibleDateTimeValue = input.toString();
      if (looksLikeLocalDateTime(possibleDateTimeValue)) {
        return LocalDateTime.parse(possibleDateTimeValue,
            DateTimeFormatter.ofPattern(DATE_PATTERN));
      }
    }
    throw new CoercingParseValueException(
        "Unable to parse variable value " + input + " as LocalDateTime");
  }

  private static LocalDateTime parseLocalDateTimeFromAstLiteral(Object input) {
    if (input instanceof StringValue) {
      String possibleDateTimeValue = ((StringValue) input).getValue();
      if (looksLikeLocalDateTime(possibleDateTimeValue)) {
        return LocalDateTime.parse(possibleDateTimeValue,
            DateTimeFormatter.ofPattern(DATE_PATTERN));
      }
    }
    throw new CoercingParseLiteralException(
        "Value is not a valid LocalDateTime: '" + String.valueOf(input) + "'"
    );
  }
}