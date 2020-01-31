# together MySQL on Kubernetes

Use the following commands to deploy or expose mysql on kubernetes cluster
```bash
  kubectl apply -f mysql-deployment.yaml
  kubectl delete -n default deployment mysql
  kubectl apply -f mysql-pv.yaml
  kubectl apply -f mysql-deployment.yaml
  kubectl describe deployment mysql
  kubectl expose deployment mysql --type=LoadBalancer --name=mysql-deployment
```
