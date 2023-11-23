#
# Build stage
#
FROM maven:3.8.3-openjdk-17-slim AS build
COPY . /app/game-api/
RUN mvn -f /app/game-api/ clean package

#
# Package stage
#
FROM openjdk:latest
RUN  echo --from=build /app/game-api/target/game-api.jar
COPY --from=build /app/game-api/target/game-api.jar /usr/local/lib/game-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000","-jar","/usr/local/lib/game-api.jar"]