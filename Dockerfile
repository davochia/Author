#FROM maven:3.6.3 AS maven
#MAINTAINER dattech.com
#
#RUN mvn clean package
#
#
#FROM amazoncorretto:17
#
#MAINTAINER dattech.com
#
#COPY target*.jar author-server-1.0.0.jar
#
#ENTRYPOINT ["java","-jar","/author-server-1.0.0.jar"]