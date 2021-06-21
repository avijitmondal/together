# together-conversation module

clone and goto together directory

```bash
cd together/together-conversation
```

Use the following commands to create together-conversation docker image
```bash
docker build . -t together-conversation:latest
```

to run together-conversation docker image as a docker container
```bash
docker run --port 8080:8080 together-conversation:latest
```

Open browser and goto 
http://localhost:8080
to get the conversation
