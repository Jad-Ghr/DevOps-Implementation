FROM maven:3.9.4-eclipse-temurin-11 AS build

WORKDIR /workspace

COPY . ./
RUN mvn -B -DskipTests -f api-gateway-service/pom.xml package

FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
WORKDIR /app
COPY --from=build /workspace/api-gateway-service/target/*.jar app.jar
EXPOSE 8090
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]