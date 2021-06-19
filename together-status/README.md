# together-status module

clone and goto together-server directory

```bash
cd together-server/together-status
```

Use the following commands to create together-status docker image
```bash
docker build . -t together-status:latest
```

to run together-status docker image as a docker container
```bash
docker run --port 8080:8080 together-status:latest
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-status/k8s-together-status.yml
```
Open browser and goto 
http://localhost:8080
to get the status
