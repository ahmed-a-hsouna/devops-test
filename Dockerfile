FROM openjdk:11
WORKDIR /app
COPY target/DevOps_Project-1.0.jar /app/DevOps_Project.jar
EXPOSE 8080
CMD ["java", "-jar", "devops-project.jar"]
