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
