PROJECT INIT RENEWAL
====================

소개
----
이전 수업 내용 [01. project-init](https://github.com/StudyIsEasy/fastcampus-studyadmin/tree/feature/01-projectinit) 의 수정 버전 입니다.
수정이 된 부분은 아래와 같습니다.
***

#### 동일한 부분
* Lombok annotation processing 설정 <br>
window : 왼쪽 상단 위 file -> settings -> build, Execution, Deveployment -> Compiler > Annotation Processors -> Enable annotation prossing 체크 <br><br>
macOS : 왼쪽 상단 위 IntelliJ IDEA -> Preferences ->  build, Execution, Deveployment -> Compiler > Annotation Processors -> Enable annotation prossing 체크 <br>

<img src="/01.project-init/images/20201017_151448.png" width="800" height="700"></img>



#### 변경점 ( 필수 변경 X ) 기존 강의대로 진행 해도 동일 합니다.
* src/main/java/resources/application.properties -> src/main/java/resources/application.yaml <br>
기존에 properties 를 사용하였습니다. properties 는 한줄에 1가지 값을 지정 하므로, 조금더 간결한 yaml 로 변경<br>
기본으로 생성된 application.properties 를 rename 하여 확장자를 yaml로 변경 해 주시면 됩니다. <br>
<br>
적용 비교

[before] src/main/java/resources/application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/fastcampus?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=rootroot
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.application.name=admin

logging.level.com.zaxxer.hikari.HikariConfig=DEBUG
logging.level.com.zaxxer.hikari=TRACE
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

```

[after] src/main/java/resources/application.yaml
```

spring:
  application:
    name: back-office
  jpa:
    show-sql: true
  datasource:
    url: jdbc:mysql://localhost:3306/fastcampus?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true
    username: root
    password: rootroot
    driver-class-name: com.mysql.cj.jdbc.Driver

logging:
  level:
    root: info
    com.zaxxer.hikari.HikariConfig: debug
    org.hibernate.SQL: debug
    hibernate.type.descriptor.sql.BasicBinder: trace
```
<br><br>

* MySQL 설치를 docker로 대체 ( 필수 X )
기존에는 직접 MySQL을 다운 받아서 설치 하였습니다. 설치 하는 과정에서 여러가지 어려운 점이 있었거나 할 수 있음으로, 
docker를 사용하시는 분들은 새로운 프로젝트에 추가된 docker-compose.yaml를 docker-compose up 으로 실행하여 사용하셔도 됩니다.


[docker-compose.yaml]
```
version: "3"
services:
  mysql:
    image: mysql:8.0.17
    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      MYSQL_ROOT_PASSWORD: "rootroot"
    command:
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    volumes:
      - G:\mysql/factcampus/backoffice/data:/var/lib/mysql # 해당 경로는 자신의 환경에 맞게 조절 하세요
```

#### MySQL SCHEMA 생성 <br><br>
1. MySQL Workbench로 접속 <br><br>
<img src="/01.project-init/images/20201017_150845.png" width="1200" height="500"></img><br><br>

2. 왼쪽 상단 + sql 아이콘 클릭 sql 명령어 실행 <br><br>
<img src="/01.project-init/images/20201017_163055.png" width="1200" height="500"></img><br><br>
    ```
    CREATE SCHEMA `fastcampus` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
    ```
3. 왼쪽 상단 새로 고침 버튼을 눌러서 생성된 Schema 확인 <br><br>
<img src="/01.project-init/images/20201017_163625.png" width="1200" height="500"></img><br><br>

4. Application 실행하여 정상 적인 실행 확인 <br><br>
<img src="/01.project-init/images/20201017_151302.png" width="1200" height="500"></img><br><br>

