# together-user module

clone and goto together directory

```bash
cd together/together-user
```

Use the following commands to create together-user docker image
```bash
docker build . -t together-user:latest
```

to run together-user docker image as a docker container
```bash
docker run --port 8080:8080 together-user:latest
```

Open browser and goto 
http://localhost:8080
to get the user
