# together-database module

clone and goto together directory

```bash
cd together/together-database
```

Use the following commands to create together-database docker image
```bash
docker build . -t together-database:latest
```

to run together-database docker image as a docker container
```bash
docker run --port 8080:8080 together-database:latest
```

Open browser and goto 
http://localhost:8080
to get the database
