# together-message module

clone and goto together directory

```bash
cd together/together-message
```

Use the following commands to create together-message docker image
```bash
docker build . -t together-message:latest
```

to run together-message docker image as a docker container
```bash
docker run --port 8080:8080 together-message:latest
```

Open browser and goto 
http://localhost:8080
to get the message
