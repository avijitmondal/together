# together no dependency module dockerfile

Use the following commands to create docker image and run as a container
```bash
docker build . --no-cache --build-arg MODULE=together-gateway --build-arg PORT=8887 -f docker/together-module.dev.Dockerfile -t together-gateway
```

to run as a docker image
```bash
docker run --port 8761:8761 together-status
```

after creation of docker image, use the below kubernetes command to deploy it in cluster

```bash
kubectl run together-gateway --image=together-gateway --image-pull-policy=Never --port=8887
kubectl expose deployment together-gateway --type=LoadBalancer --name=together-gateway-expose
```