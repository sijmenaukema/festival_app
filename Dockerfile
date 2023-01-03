FROM maven:3.8.3-openjdk-17-slim AS Build
ADD target/festival-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]

