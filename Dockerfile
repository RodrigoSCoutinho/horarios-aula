FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/horarios-aula-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]