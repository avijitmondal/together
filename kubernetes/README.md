# together MySQL 

clone and goto together-server directory

```bash
cd together-server
```

use the below kubernetes command to deploy mysql in K8s cluster

```bash
kubectl apply -f kubernetes/k8s-mysql-pvc.yml
kubectl apply -f kubernetes/k8s-mysql-deployment.yml
```

Services exposed to http://localhost:3306
