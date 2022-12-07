FROM maven:3.8.6-openjdk-18-slim

ADD target/round-svc.jar round-svc.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/round-svc.jar"]
