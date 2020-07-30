# spring-security-polling

Mysql DB는 docker로 실

```shell script
docker run --name mysql_db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql
```
첫 시작시 import sql을 위한 initialize true로 바꾸어주기
```properties
initialize: false
```