# together-discovery module

clone and goto together directory

```bash
cd together/together-discovery
```

Use the following commands to create together-discovery docker image
```bash
docker build . -t together-discovery:latest
```

to run together-discovery docker image as a docker container
```bash
docker run --port 8080:8080 together-discovery:latest
```

Open browser and goto 
http://localhost:8080
to get the discovery
