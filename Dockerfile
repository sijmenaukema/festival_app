FROM openjdk:17
MAINTAINER capgemini.nl
COPY target/festival-0.0.1-SNAPSHOT.jar festival_app.jar
ENTRYPOINT ["java","-jar","/festival_app.jar"]
EXPOSE 9090