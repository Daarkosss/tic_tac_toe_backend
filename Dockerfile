FROM eclipse-temurin:19-jdk as builder

# set the working directory
WORKDIR /opt/app

# copy the maven wrapper files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

# download dependencies
RUN ./mvnw dependency:go-offline
# copy the source code
COPY ./src ./src

# build the application
RUN ./mvnw clean install -DskipTests

# second stage
FROM eclipse-temurin:19-jre
# set the working directory
WORKDIR /opt/app
# expose the port
EXPOSE 8080
# copy the artifact from the first stage
COPY --from=builder /opt/app/target/tic_tac_toe_backend-0.0.1-SNAPSHOT.jar /opt/app/tic_tac_toe_backend-0.0.1-SNAPSHOT.jar
# set the entrypoint
ENTRYPOINT ["java", "-jar", "/opt/app/tic_tac_toe_backend-0.0.1-SNAPSHOT.jar"]