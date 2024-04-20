FROM ubuntu:latest

RUN apt-get update -y && \
    apt-get install -y openjdk-22-jdk maven

ENTRYPOINT [ "mvn", "spring-boot:run", "-DskipTests", "-Dmaven.test.skip=true" ]
