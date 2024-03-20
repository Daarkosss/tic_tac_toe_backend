FROM eclipse-temurin:19-jdk as builder

# Set the working directory
WORKDIR /app

# Copy the maven wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

# Download dependencies, to avoid re-downloading them on each build
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY ./src ./src

# Build the application
RUN ./mvnw clean install -DskipTests

# Second stage
FROM eclipse-temurin:19-jre

# Set the working directory
WORKDIR /app

# Expose the port
EXPOSE 8080

# Copy the artifact from the first stage
COPY --from=builder /app/target/*.jar app.jar

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]