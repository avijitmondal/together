# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
LABEL maintainer="avijitmondal38@gmail.com"

WORKDIR /app

ADD together-user/src/ together-user/src/
ADD together-user/pom.xml together-user/pom.xml
ADD settings.xml ~/.m2/settings.xml

RUN mvn --file together-user/pom.xml clean package
RUN mv together-user/target/together-user-*.jar app.jar

# Deployment stage
FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/app.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
