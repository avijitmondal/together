# together-discovery module

clone and goto together-server directory

```bash
cd together-server/together-discovery
```

Use the following commands to create together-discovery docker image
```bash
docker build .  -t together-discovery:latest
```

to run together-discovery docker image as a docker container
```bash
docker run --port 8761:87611 together-discovery:latest
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-discovery/k8s-together-discovery.yml
```

Open browser and goto http://localhost:8761 to get the eureka dashboard