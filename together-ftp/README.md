# together-ftp module

clone and goto together directory

```bash
cd together/together-ftp
```

Use the following commands to create together-ftp docker image
```bash
docker build . -t together-ftp:latest
```

to run together-ftp docker image as a docker container
```bash
docker run --port 8080:8080 together-ftp:latest
```

Open browser and goto 
http://localhost:8080
to get the ftp
