# FASTCAMPUS-BACKOFFICE

FastCampus Java 바이트 디그리 과정 Renewal
======

* 기존에 제공 되어 졌었던 패스트 캠퍼스 어드민 개발을 현재의 버전에 맞게 Renewal 합니다.<br>
<br>

#### 필수 변경점
* Java 버전 변경 
  - 기존 1.8 , 변경 11
  
* Spring Boot 버전 변경
  - 기존 2.2.6 , 변경 2.3.4
  
* Lombok 사용법
* DI 부분 Autowired -> 생성자 주입 패턴으로 변경 
<br><br>


#### 부분 변경점
* [project-init](https://github.com/steve-developer/fastcampus-backoffice/tree/feature/01-project-init/01.project-init) 
에서의 변경
  - application.properties -> application.yaml
  - mySQL 설치 대신 docker 사용자들을 위한 docker-compose.yaml 제공
  

#### 변경된 프로젝트 생성 방법
* JDK 설치는 기존과 동일합니다. 버전만 11 버전으로 설치 하시면 됩니다.<br>

* 프로젝트 생성 <br><br>
1.  Spring Initializr 에서 Project SDK 를 JAVA 11버전을 선택 합니다. <br><br>
  <img src="/images/20201017_144659.png" width="1200" height="500"></img><br><br>
2. Dependencies 에서 Developer Tools -> Lombok 를 선택 <br><br>
  <img src="/images/20201017_144810.png" width="1200" height="500"></img><br><br>
3. Dependencies 에서 Web -> Spring Web 를 선택 <br><br>
  <img src="/images/20201017_144834.png" width="1200" height="500"></img><br><br>
4. Dependencies 에서 SQL -> Spring Data JPA, MySql Driver 를 선택 <br><br>
  <img src="/images/20201017_144958.png" width="1200" height="500"></img><br><br>
5. 이후 프로젝트 첫번째 강의 init project 참고 해주세요 [Link](https://github.com/steve-developer/fastcampus-backoffice/tree/feature/01-project-init/01.project-init) 을 참고 하세요

#### 업데이트 된 build.gradle 파일

[before]
```
plugins {
    id 'org.springframework.boot' version '2.2.6.RELEASE'
    id 'io.spring.dependency-management' version '1.0.9.RELEASE'
    id 'java'
}

group = 'com.fastcampus'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
}

test {
    useJUnitPlatform()
}

```


[after]
```
plugins {
    id 'org.springframework.boot' version '2.3.4.RELEASE'
    id 'io.spring.dependency-management' version '1.0.10.RELEASE'
    id 'java'
}

group = 'com.fastcampus'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
        exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }
}

test {
    useJUnitPlatform()
}
```