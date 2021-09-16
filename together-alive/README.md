# together-alive module

clone and goto together directory

```bash
cd together/together-alive
```

Use the following commands to create together-alive docker image
```bash
docker build . -t together-alive:latest
```

to run together-alive docker image as a docker container
```bash
docker run --port 8080:8080 together-alive:latest
```

Open browser and goto 
http://localhost:8080
to get the alive
