FROM openjdk:8-jdk-alpine
LABEL maintainer="ritam.banik@gmail.com"
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=build/libs/namegame-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} namegame-challenge-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/namegame-challenge-service.jar"]