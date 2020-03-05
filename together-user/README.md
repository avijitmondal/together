# together-user module

clone and goto together-server directory

```bash
cd together-server
```

Use the following commands to create together-user docker image
```bash
docker build . --no-cache --build-arg MODULE=together-user --build-arg PORT=8901 -f docker/together-dependency.dev.Dockerfile -t together-user
```

to run together-user docker image as a docker container
```bash
docker run --port 8901:8901 together-user
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-user/k8s-together-user.yml
```

Services exposed to http://localhost:8901 
