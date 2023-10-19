FROM openjdk:11
WORKDIR /app

# Copy the JAR file from the target directory to the container
COPY target/DevOps_Project-1.0.jar /app/DevOps_Project.jar
EXPOSE 8080
# Command to run the Spring Boot applica
CMD ["java", "-jar", "DevOps_Project.jar"]
