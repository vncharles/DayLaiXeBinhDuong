# Use a base image with Java 11 installed
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/website-0.0.1-SNAPSHOT.jar /app

# Expose the port that the Spring Boot application listens on
EXPOSE 8080

# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "website-0.0.1-SNAPSHOT.jar"]
