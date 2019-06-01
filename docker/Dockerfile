FROM ubuntu:18.04
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

# Adding jdk other dependencies to docker image
ADD lib/jdk-8u211-linux-x64.tar.gz /var/lib/jdk
ADD lib/apache-maven-3.6.1-bin.tar.gz /var/lib
ADD lib/apache-tomcat-9.0.20.tar.gz /var/lib

# Setting up environment variables
ENV JAVA_HOME "/var/lib/jdk/jdk1.8.0_211"
ENV M2_HOME "/var/lib/apache-maven-3.6.1"
ENV CATALINA_HOME "/var/lib/apache-tomcat-9.0.20"
ENV PATH "${PATH}:${JAVA_HOME}/bin:${M2_HOME}/bin:${CATALINA_HOME}/bin"

# Copying source code
WORKDIR /app
ADD src/ src/
ADD pom.xml pom.xml

# Prepare by downloading dependencies
RUN ["mvn", "clean"]
RUN ["mvn", "package"]
RUN ["mvn", "verify"]

RUN ["mkdir", "/var/lib/apache-tomcat-9.0.20/webapps/admin"]
RUN ["mv", "/var/lib/apache-tomcat-9.0.20/webapps/ROOT", "/var/lib/apache-tomcat-9.0.20/webapps/admin"]
RUN ["mv", "target/together-server.war", "/var/lib/apache-tomcat-9.0.20/webapps/ROOT.war"]

EXPOSE 8080
CMD ["/var/lib/apache-tomcat-9.0.20/bin/catalina.sh", "run"]
