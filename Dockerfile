# Etap build
FROM maven:3.9.6-eclipse-temurin-21 AS build
LABEL org.opencontainers.image.authors="Jakub Dziem"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etap run
FROM eclipse-temurin:21-jre
LABEL org.opencontainers.image.authors="Jakub Dziem"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
HEALTHCHECK --interval=10s --timeout=5s\
    CMD curl -f http://localhost:8080 || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]
