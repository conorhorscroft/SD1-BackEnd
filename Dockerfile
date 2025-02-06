# Use Maven image for building
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
# Copy project files
COPY . .
# Build JAR file with Spring Boot Maven plugin
RUN mvn clean package spring-boot:repackage -DskipTests

# Use slim OpenJDK image for runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copy built JAR from previous stage
COPY --from=build /app/target/SlainteFit-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.main.main-class=com.example.SlainteFit.SlainteFitApplication"]
