FROM openjdk:8-alpine
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

# Adding other dependencies to docker image
ADD lib/apache-maven-3.6.1-bin.tar.gz /var/lib
ADD lib/apache-tomcat-9.0.20.tar.gz /var/lib

# Setting up environment variables
ENV M2_HOME "/var/lib/apache-maven-3.6.1"
ENV CATALINA_HOME "/var/lib/apache-tomcat-9.0.20"
ENV PATH "${PATH}:${M2_HOME}/bin:${CATALINA_HOME}/bin"

# Copying source code
WORKDIR /app
ADD src/ src/
ADD environment/ environment/
ADD pom.xml pom.xml

# Prepare by downloading dependencies
RUN ["mvn", "clean"]
RUN ["mvn", "package"]
RUN ["mvn", "verify"]

RUN ["mkdir", "/var/lib/apache-tomcat-9.0.20/webapps/admin"]
RUN ["mv", "/var/lib/apache-tomcat-9.0.20/webapps/ROOT", "/var/lib/apache-tomcat-9.0.20/webapps/admin"]
RUN ["mv", "target/together-server.war", "/var/lib/apache-tomcat-9.0.20/webapps/ROOT.war"]
RUN ["mv", "/app/environment/server.xml", "/var/lib/apache-tomcat-9.0.20/conf/"]
RUN ["mv", "/app/environment/together-keystore", "/var/lib/apache-tomcat-9.0.20/conf/"]

EXPOSE 8443
CMD ["/var/lib/apache-tomcat-9.0.20/bin/catalina.sh", "run"]
