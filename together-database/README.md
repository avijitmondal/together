# together-database module

clone and goto together directory

```bash
cd together
```

Use the following commands to create together-database docker image
```bash
docker build . --build-arg MODULE=together-database --build-arg PORT=8902 -f docker/together-dependency.dev.Dockerfile -t together-database
```

to run together-discovery docker image as a docker container
```bash
docker run --port 8902:8902 together-database
```

after creation of docker image, use the below kubernetes command to deploy it in K8s cluster

```bash
kubectl apply -f together-database/k8s-together-database.yml
```

Services exposed to http://localhost:8902
Swagger UI is available at http://localhost:8902/swagger-ui.html
