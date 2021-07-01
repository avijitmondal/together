# together-auth-center module

clone and goto together directory

```bash
cd together/together-auth-center
```

Use the following commands to create together-auth-center docker image
```bash
docker build . -t together-auth-center:latest
```

to run together-auth-center docker image as a docker container
```bash
docker run --port 8080:8080 together-auth-center:latest
```

Open browser and goto 
http://localhost:8080
to get the auth-center


Accessing unsecured resource
```shell
curl http://localhost:8906/unsecured
This resource is not secured
```

Accessing secured resource as user
```shell
curl --user user:P@ssw0rd http://localhost:8906/secured
This resource is secured. Authentication: user; Authorities: [ROLE_USER]
```

Accessing secured resource as admin
```shell
curl --user P@ssw0rd:P@ssw0rd http://localhost:8906/secured
This resource is secured. Authentication: admin; Authorities: [ROLE_ADMIN, ROLE_USER]
```
