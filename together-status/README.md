# together-status module

clone and goto together directory

```bash
cd together/together-status
```

Use the following commands to create together-status docker image
```bash
docker build . -t together-status:latest
```

to run together-status docker image as a docker container
```bash
docker run --port 8080:8080 together-status:latest
```

Open browser and goto 
http://localhost:8080
to get the status
