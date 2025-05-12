# Budowa aplikacji Java
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Autor obrazu zgodnie ze standardem OCI
LABEL org.opencontainers.image.authors="Jakub Dziem"
# Ustawienie katalogu roboczego
WORKDIR /app
# Kopiowanie pliku z zależnościami
COPY pom.xml .
#  Kopiowanie kodu źródłowego aplikacji
COPY src ./src
# Instalacja zależności i budowa aplikacji bez uruchamiania testów
RUN mvn clean package -DskipTests

# Druga część obrazu - JRE dla uruchomienia aplikacji
FROM eclipse-temurin:21-jre
# Autor obrazu zgodnie ze standardem OCI
LABEL org.opencontainers.image.authors="Jakub Dziem"
# Ustawienie katalogu roboczego
WORKDIR /app
# Kopiowanie aplikacji Java
COPY --from=build /app/target/*.jar app.jar
# Eksponowanie portów
EXPOSE 8080
# Dodanie HEALTHCHECK do sprawdzania dostępności serwisów
HEALTHCHECK --interval=10s --timeout=5s\
    CMD curl -f http://localhost:8080 || exit 1
# Uruchomienie aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]
