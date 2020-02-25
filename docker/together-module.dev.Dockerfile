#More details check dockerfile-together-module-README.md
# Build stage
FROM maven:3.6.1-jdk-11 AS build
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

ARG MODULE
ARG PORT
ARG SKIP_TEST=true
ENV CORE_MODULE together-core
ENV MODULE_NAME ${MODULE}
ENV MODULE_PORT ${PORT}
ENV SKIP_TESTS=${SKIP_TEST}

# Copying source code
WORKDIR /app
ADD together-server.pom.xml pom.xml
ADD ${CORE_MODULE}/src/ ${CORE_MODULE}/src/
ADD ${CORE_MODULE}/pom.xml ${CORE_MODULE}/pom.xml
ADD ${MODULE_NAME}/src/ ${MODULE_NAME}/src/
ADD ${MODULE_NAME}/pom.xml ${MODULE_NAME}/pom.xml

# Prepare by downloading dependencies core module
RUN mvn --file ${CORE_MODULE}/pom.xml clean install -DskipTests=${SKIP_TESTS}
# Prepare by downloading dependencies main module
RUN mvn --file ${MODULE_NAME}/pom.xml clean package -DskipTests=${SKIP_TESTS}

# Package stage
FROM openjdk:11-jre-slim
MAINTAINER Avijit Mondal <avijitmondal38@gmail.com>

ARG MODULE
ARG PORT

COPY --from=build /app/${MODULE}/target/${MODULE}.jar /usr/local/lib/app.jar

EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
