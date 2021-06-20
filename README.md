# *together* #

[![together CI](https://github.com/avijitmondal/together-server/workflows/together%20CI/badge.svg)](https://github.com/avijitmondal/together-server/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

together server is the backend for together application.

### What is this repository for? ###

* Rest server together chat application
* 1.0.5-SNAPSHOT
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* [Summary of set up](#Summary of set up)
* [Configuration](#Configuration)
* [Dependencies](#Dependencies)
* [Database configuration](#Database configuration)
* [How to run](#How to run)
* [Deployment instructions](#Deployment instructions)

### Summary of set up
Docker used to deploy this application

### Configuration
######Configure Tomcat to support SSL or https
generate keystore 
```docker
$keytool -genkey -alias together -keyalg RSA -keystore together-keystore
$keytool -importkeystore -srckeystore together-keystore -destkeystore together-keystore -deststoretype pkcs12
``` 
keystore password : together

### Dependencies
Docker version
```sbtshell
avijit$ docker version

Client: Docker Engine - Community
 Version:           18.09.2
 API version:       1.39
 Go version:        go1.10.8
 Git commit:        6247962
 Built:             Sun Feb 10 04:12:39 2019
 OS/Arch:           darwin/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          18.09.2
  API version:      1.39 (minimum version 1.12)
  Go version:       go1.10.6
  Git commit:       6247962
  Built:            Sun Feb 10 04:13:06 2019
  OS/Arch:          linux/amd64
  Experimental:     false
```
Docker-compose version
```docker
avijit$ docker-compose version
docker-compose version 1.23.2, build 1110ad01
docker-py version: 3.6.0
CPython version: 3.6.6
OpenSSL version: OpenSSL 1.1.0h  27 Mar 2018
```

### Database configuration
mysql-server:5.7 is being used

### How to run
Run docker compose commands in source directory
```docker
docker-compose build
docker-compose up
```

### Deployment instructions
Open
```https://<container-ip>:8443```
for swagger UI.

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* [Avijit Mondal](mailto:avijitmondal38@gmail.com)
