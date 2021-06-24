# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
LABEL maintainer="avijitmondal38@gmail.com"

WORKDIR /app

ADD together-ftp/src/ together-ftp/src/
ADD together-ftp/pom.xml together-ftp/pom.xml
ADD settings.xml ~/.m2/settings.xml

RUN mvn --file together-ftp/pom.xml clean package
RUN mv together-ftp/target/together-ftp-*.jar app.jar

# Deployment stage
FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/app.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
