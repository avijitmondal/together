FROM openjdk:11-jre-slim
LABEL maintainer="avijitmondal38@gmail.com"

ADD together-conversation/target/together-conversation-*.jar /usr/local/lib/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
