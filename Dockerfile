FROM openjdk:8-jdk-alpine
MAINTAINER joksrc_coe
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/asfinder_producer-1.jar
ADD ${JAR_FILE} asfinder-docker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/asfinder-docker.jar"]