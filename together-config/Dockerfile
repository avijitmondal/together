# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
LABEL maintainer="avijitmondal38@gmail.com"

WORKDIR /app

ADD together-config/src/ together-config/src/
ADD together-config/pom.xml together-config/pom.xml
ADD settings.xml settings.xml

RUN mvn --file together-config/pom.xml -s settings.xml clean package
RUN mv together-config/target/together-config-*.jar app.jar

# Deployment stage
FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/app.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
