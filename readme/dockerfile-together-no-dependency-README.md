# together no dependency module dockerfile

Use the following commands to create docker image and run as a container
```bash
docker build . --no-cache --build-arg MODULE=together-status --build-arg PORT=8761 -f docker/Dockerfile.together-status.dev -t together-status
docker run --port 8761:8761 together-status
```
