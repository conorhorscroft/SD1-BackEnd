# Use a basic Maven image to build the project
FROM maven:3.8.7 AS build

# Install OpenJDK 17 manually
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set the Java environment variables for Apple Silicon
ENV JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

# Copy the entire project to the container
COPY . .

# Check Java version to ensure it's installed correctly
RUN java -version

# Build the JAR file (adjust the path if necessary)
RUN mvn clean package -DskipTests -X

# Use a lightweight OpenJDK 17 image to run the application
FROM openjdk:17-jdk-slim

# Set the Java environment variables again in the second stage
ENV JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/SlainteFit-0.0.1-SNAPSHOT.jar app.jar

# Expose the port Spring Boot runs on (default: 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
