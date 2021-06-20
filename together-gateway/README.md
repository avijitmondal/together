# together-gateway module

clone and goto together directory

```bash
cd together
```

Use the following commands to create together-gateway docker image
```bash
docker build . --no-cache --build-arg MODULE=together-gateway --build-arg PORT=8900 -f docker/together-no-dependency.dev.Dockerfile -t together-gateway
```

to run together-discovery docker image as a docker container
```bash
docker run --port 8900:8900 together-gateway
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-gateway/k8s-together-gateway.yml
```

Services exposed to http://localhost:8900
