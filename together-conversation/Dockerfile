# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
LABEL maintainer="avijitmondal38@gmail.com"

WORKDIR /app
ADD together.pom.xml pom.xml
ADD together-core/src/ together-core/src/
ADD together-core/pom.xml together-core/pom.xml
ADD together-conversation/src/ together-conversation/src/
ADD together-conversation/pom.xml together-conversation/pom.xml

RUN mvn --file together-core/pom.xml clean install

RUN mvn --file together-conversation/pom.xml clean package
RUN mv together-conversation/target/together-alive-*.jar app.jar

# Deployment stage
FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/app.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
