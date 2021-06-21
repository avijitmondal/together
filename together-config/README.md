# together-config module

clone and goto together directory

```bash
cd together/together-config
```

Use the following commands to create together-config docker image
```bash
docker build . -t together-config:latest
```

to run together-config docker image as a docker container
```bash
docker run --port 8080:8080 together-config:latest
```

Open browser and goto 
http://localhost:8080
to get the config
