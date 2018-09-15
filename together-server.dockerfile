FROM openjdk:8
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

#Install maven
RUN apt-get update
RUN apt-get install -y maven

#Adding source compile and package into a fat jar
WORKDIR /app
ADD pom.xml /app/pom.xml

#Prepare by downloading dependencies
#RUN ["mvn", "dependency:resolve"]
#RUN ["mvn", "verify"]

ADD src/ /app/src/
RUN ["mvn", "package"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/together-server.jar"]

