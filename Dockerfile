FROM maven:3.8.1-openjdk-17-slim AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean package


FROM amazoncorretto:17
#FROM apache/skywalking-java-agent:8.5.0-jdk8
MAINTAINER dattech.com

#ENV SW_AGENT_NAME=codewisdom/ts-travel2-service:0.2.0
#ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800

#ENV javaagent=/Users/kingdavid/Downloads/skywalking-agent/skywalking-agent.jar
#ENV Dskywalking.agent.service_name=codewisdom/ts-travel2-service:0.2.0
#ENV Dskywalking.collector.backend_service=127.0. 0.1:11800

COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java",  "-jar", "/app.jar"]


