# Build stage: use the entire backend as context so module dependencies can be built
FROM maven:3.9.4-eclipse-temurin-11 AS build

WORKDIR /workspace

# Copy the whole backend (set docker build context accordingly in docker-compose)
COPY . ./

# Build and install module dependencies, then package user-service
RUN mvn -B -DskipTests -f common-service/pom.xml install \
 && mvn -B -DskipTests -f common-student/pom.xml install \
 && mvn -B -DskipTests -f user-service/pom.xml package

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

WORKDIR /app

# Copy the built jar from the Maven build stage (module path)
COPY --from=build /workspace/user-service/target/*.jar app.jar

ENV PORT=8080

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar app.jar"]