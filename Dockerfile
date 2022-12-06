FROM openjdk:11-jdk-slim

WORKDIR /app

COPY build/libs/docker_ci-0.0.1-SNAPSHOT.jar app.jar

ENV PORT 8075

ENTRYPOINT ["java","-Dserver.port=${PORT}","-Dspring.profiles.active=prod", "-jar", "app.jar"]