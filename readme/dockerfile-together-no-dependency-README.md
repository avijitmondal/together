# together no dependency module dockerfile

Use the following commands to create docker image and run as a container
```bash
docker build . --no-cache --build-arg MODULE=together-status --build-arg PORT=8761 -f docker/together-no-dependency.dev.Dockerfile -t together-status
```

to run as a docker image
```bash
docker run --port 8761:8761 together-status
```

after creation of docker image, use the below kubernetes command to deploy it in cluster

```bash
kubectl run together-status --image=together-status --image-pull-policy=Never --port=8761
kubectl expose deployment together-status --type=LoadBalancer --name=together-status-expose
```