# together-auth-center module

clone and goto together directory

```bash
cd together/together-auth-center
```

Use the following commands to create together-auth-center docker image
```bash
docker build . -t together-auth-center:latest
```

to run together-auth-center docker image as a docker container
```bash
docker run --port 8080:8080 together-auth-center:latest
```

Open browser and goto 
http://localhost:8080
to get the auth-center

swagger-ui is available at 
http://localhost:8906/swagger-ui.html

h2 console is available at
http://localhost:8906/h2-ui

Generating access token for ROLE_USER
```shell
curl http://localhost:8906/oauth/token \
	-d"grant_type=password&username=user1@example.com&password=password" \
	-H"Content-type:application/x-www-form-urlencoded; charset=utf-8" \
	-u myclient:secret
```
```shell
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjUyNDYyNDYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoiYzVhOWU3MTAtNzk5Zi00ODkyLTk4YzUtYzUzMjI2NTNjMTFhIiwiY2xpZW50X2lkIjoibXljbGllbnQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.nH4SoBBhcuW-th0AbJFHdIoj89aBEc8ES7LduHCffpA",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImM1YTllNzEwLTc5OWYtNDg5Mi05OGM1LWM1MzIyNjUzYzExYSIsImV4cCI6MTYyNzc1MTg0NiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjliMGZlYmNlLWUzOWUtNDZmZi05ZGFhLThlODJhNmY0YjNhZSIsImNsaWVudF9pZCI6Im15Y2xpZW50In0.WYF1FUeZGaIWc-nTm447UgsedX9kgf2uU4fK-Zv-lC8",
  "expires_in": 86399,
  "scope": "read write",
  "jti": "c5a9e710-799f-4892-98c5-c5322653c11a"
}
```

Generating access token for ROLE_ADMIN
```shell
curl http://localhost:8906/oauth/token \
	-d"grant_type=password&username=admin@example.com&password=password" \
	-H"Content-type:application/x-www-form-urlencoded; charset=utf-8" \
	-u myclient:secret
```
```shell
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjUyNDYyODksInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiIwMTVjZDMwYS0yNGQ5LTQ4MTYtOGRiYS05MTc2NjY1NzY3MmEiLCJjbGllbnRfaWQiOiJteWNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.T-R-WYccrppuRSuqFqe9noAcgV4nSv-Rg2ZoZ0v8kcU",
  "token_type": "bearer",
  "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIwMTVjZDMwYS0yNGQ5LTQ4MTYtOGRiYS05MTc2NjY1NzY3MmEiLCJleHAiOjE2Mjc3NTE4ODksImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iLCJST0xFX1VTRVIiXSwianRpIjoiOWI0M2MwMDktYWNmMi00YzliLWFiMjEtMmNmZmFiY2JkMWI0IiwiY2xpZW50X2lkIjoibXljbGllbnQifQ.zuS8EkcWCY06JZjnrvxaA36hskAn5aXV_OGVP8L2prQ",
  "expires_in": 86400,
  "scope": "read write",
  "jti": "015cd30a-24d9-4816-8dba-91766657672a"
}
```

Accessing unsecured resource
```shell
curl http://localhost:8906/unsecured
This resource is not secured
```

Accessing secured resource as user
```shell
curl http://localhost:8906/secured/user -H"Authorization: Bearer $ACCESS_TOKEN_FOR_ROLE_USER"
This resource is secured. Authentication: user; Authorities: [ROLE_USER]
```

Accessing secured resource as admin
```shell
curl http://localhost:8906/secured/admin -H"Authorization: Bearer $ACCESS_TOKEN_FOR_ROLE_ADMIN"
This resource is secured. Authentication: admin; Authorities: [ROLE_ADMIN, ROLE_USER]
```
