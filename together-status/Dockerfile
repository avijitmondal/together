# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
LABEL maintainer="avijitmondal38@gmail.com"

WORKDIR /app

ADD together-status/src/ together-status/src/
ADD together-status/pom.xml together-status/pom.xml
ADD settings.xml settings.xml

RUN mvn --file together-status/pom.xml -s settings.xml clean package
RUN mv together-status/target/together-status-*.jar target/app.jar

# Deployment stage
FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/target/app.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
