FROM --platform=linux/amd64 openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/sfsb-0.2.3-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]

