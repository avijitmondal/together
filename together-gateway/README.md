# together-gateway module

clone and goto together directory

```bash
cd together/together-gateway
```

Use the following commands to create together-gateway docker image
```bash
docker build . -t together-gateway:latest
```

to run together-gateway docker image as a docker container
```bash
docker run --port 8080:8080 together-gateway:latest
```

Open browser and goto 
http://localhost:8080
to get the gateway
