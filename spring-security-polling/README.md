# spring-security-polling

Mysql DB는 docker로 실

```shell script
docker run --name mysql_db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -d mysql
```
첫 시작시 import sql을 위한 initialize true로 바꾸어주기
```properties
initialize: false
```
compileQuerydsl 을 해주어서 generated 폴더를 생성해야 한다.
그리고 intellij 에서 사용을 위해 generated 폴더를 source 모듈로 등록한다.