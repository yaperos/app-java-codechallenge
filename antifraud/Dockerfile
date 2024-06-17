# Base image with JDK and Maven installed
FROM maven:3.8.4-openjdk-17 AS build

# Sets the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Verify the build output
RUN ls -l /app/target

# Small base image for runtime
FROM openjdk:17-jdk-slim

# Sets the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage into the container
COPY --from=build /app/target/antifraud-0.0.1-SNAPSHOT.jar app.jar

# Specifies the command to run on container start
CMD ["java", "-jar", "app.jar"]
