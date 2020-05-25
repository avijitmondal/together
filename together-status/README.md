# together-status module

clone and goto together-server directory

```bash
cd together-server
```

Use the following commands to create together-status docker image
```bash
docker build . --no-cache --build-arg MODULE=together-status --build-arg PORT=9001 -f docker/together-no-dependency.dev.Dockerfile -t together-status
```

to run together-status docker image as a docker container
```bash
docker run --port 9001:9001 together-status
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-status/k8s-together-status.yml
```
Open browser and goto 
http://localhost:9001
to get the status
