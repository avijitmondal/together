# together-user module

clone and goto together directory

```bash
cd together
```

Use the following commands to create together-conversation docker image
```bash
docker build . --no-cache --build-arg MODULE=together-conversation --build-arg PORT=8903 -f docker/together-dependency.dev.Dockerfile -t together-conversation
```

to run together-conversation docker image as a docker container
```bash
docker run --port 8903:8903 together-conversation
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-conversation/k8s-together-conversation.yml
```

Services exposed to http://localhost:8903 
