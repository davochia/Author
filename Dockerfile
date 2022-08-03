FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean package


FROM amazoncorretto:17
#FROM apache/skywalking-java-agent:8.5.0-jdk8
MAINTAINER dattech.com

COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java",  "-jar", "/app.jar"]
#EXPOSE 8500


