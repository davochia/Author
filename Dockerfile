FROM maven:3.8.1-openjdk-17-slim AS maven

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean package


FROM openjdk:17-alpine
#FROM apache/skywalking-java-agent:8.5.0-jdk8
MAINTAINER dattech.com

RUN ["apk", "update", "&&", "apk", "-U", "upgrade"]
RUN ["apk", "add", "nano"]

COPY --from=maven /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]




