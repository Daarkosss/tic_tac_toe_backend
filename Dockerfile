# Etap 1: Budowanie aplikacji
FROM eclipse-temurin:19-jdk as builder

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie plików konfiguracyjnych Mavena i pobieranie zależności
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
RUN chmod +x ./mvnw && \
    ./mvnw dependency:go-offline

# Kopiowanie reszty kodu źródłowego i budowanie aplikacji
COPY ./src ./src
RUN ./mvnw clean package -DskipTests && \
    rm -rf /root/.m2

# Etap 2: Uruchamianie aplikacji
FROM eclipse-temurin:19-jre

# Ustawienie katalogu roboczego
WORKDIR /app

# Eksponowanie portu aplikacji
EXPOSE 8080

# Kopiowanie skompilowanego artefaktu z etapu budowania
COPY --from=builder /app/target/*.jar app.jar

# Uruchomienie aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]
