FROM maven:3.8.6-openjdk-18-slim

ADD target/rank-svc.jar rank-svc.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/rank-svc.jar"]
