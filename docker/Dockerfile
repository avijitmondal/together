FROM ubuntu:18.04
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

# Adding jdk and maven bindary to docker image
ADD lib/jdk-8u211-linux-x64.tar.gz /var/lib/jdk
ADD lib/apache-maven-3.6.1-bin.tar.gz /var/lib/apache-maven

# Setting up JAV_HOME and M2_HOME and PATH
ENV JAVA_HOME "/var/lib/jdk/jdk1.8.0_211"
ENV M2_HOME "/var/lib/apache-maven/apache-maven-3.6.1"
ENV PATH "${PATH}:${JAVA_HOME}/bin:${M2_HOME}/bin"

# Copying source code
WORKDIR /app
ADD src/ src/
ADD pom.xml pom.xml

# Prepare by downloading dependencies
RUN ["mvn", "clean"]
RUN ["mvn", "package"]
RUN ["mvn", "verify"]

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/together-server.jar"]

