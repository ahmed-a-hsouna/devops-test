FROM openjdk:11
EXPOSE 8080
ADD target/devops-project.jar devops-project.jar
CMD ["java", "-jar", "devops-project.jar"]
