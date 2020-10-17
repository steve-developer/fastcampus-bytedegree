PROJECT JPA SETUP RENEWAL
====================

소개
----
이전 수업 내용 [02. jpa-setup](https://github.com/StudyIsEasy/fastcampus-studyadmin/tree/feature/02-jpasetup) 의 수정 버전 입니다.
수정이 된 부분은 아래와 같습니다.
***

#### 동일한 부분
* Lombok annotation processing 설정 <br>
window : 왼쪽 상단 위 file -> settings -> build, Execution, Deveployment -> Compiler > Annotation Processors -> Enable annotation prossing 체크 <br><br>
macOS : 왼쪽 상단 위 IntelliJ IDEA -> Preferences ->  build, Execution, Deveployment -> Compiler > Annotation Processors -> Enable annotation prossing 체크 <br>

<img src="/01.project-init/images/20201017_151448.png" width="800" height="700"></img>

* 또는 아래처럼 프로젝트 진행시에 오른쪽 하단 경고 창을 통해서 설정 할 수 있습니다.<br><br>
<img src="/02.jpa-setup/images/20201017_172845.png" width="1200" height="500"></img>



<br><br>

#### 변경점 ( 필수 변경 X ) 기존 강의대로 진행 해도 괜찮지만 조금더 편리하게 변경 하였습니다.
* src/main/java/resources/application.properties -> src/main/java/resources/application.yaml 변경 <br>
* src/main/java/resources/application.yaml 에 jpa 관련 자동 실행 추가 <br><br>

[before 예전 application.properties]
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

[after 변경된 application.yaml]
```
spring:
  application:
    name: back-office
  jpa:
    show-sql: true
  datasource:
    url: jdbc:mysql://localhost:3306/fastcampus?useSSL=false&useUnicode=true&serverTimezone=Asia/Seoul&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true # 새로 추가된 옵션 schema 가 없는 경우 생성
    username: root
    password: rootroot
    driver-class-name: com.mysql.cj.jdbc.Driver
    schema: classpath:schema.sql  # 새로 추가된 옵션 src/main/resource/schema.sql
    initialization-mode: always   # 새로 추가된 옵션 위의 schema.sql 파일을 실행 시켜 준다.

logging:
  level:
    root: info
    com.zaxxer.hikari.HikariConfig: debug
    org.hibernate.SQL: debug
    hibernate.type.descriptor.sql.BasicBinder: trace
```

[자동실행이 잘 안되는 경우 새로 추가한 옵션 제거 application.yaml]
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

#### 잘 안되는 경우 새로 추가된 옵션을 제거 하고 아래의 순서대로 실행하여 MySQL SCHEMA 생성 <br><br>
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


#### JPA 주요내용
1. com.fastcampus.backoffice.config.JpaConfig
    ```
    @Configurable           // Spring에서 Config를 적용할때 설정하는 annotation
    @EnableJpaAuditing      // Jpa Auditing 활성화 
    public class JpaConfig {
    }
    ```
    <br>
2. EnableJpaAuditing 를 한경우 AuditorAware Component 생성하여, 수정하는 CreatedBy LastModifiedBy 대상 수정 가능<br>
    com.fastcampus.backoffice.component.AdminAuditorAware
    ```
    @Component
    public class AdminAuditorAware implements AuditorAware<String> {
    
        @Value("${spring.application.name}")        // @Value Annotation으로 application.yaml 에 정의된 내용 import
        private String serverName;
    
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of(serverName);         // serverName이 없을수도 있음으로 Optional 로 return
        }
    }
    ```
    <br>
3. Entity ( ORM 의 특성에 맞춰서 Class Object로 표현 )<br>
    com.fastcampus.backoffice.entity.**
    <br>

    | annotation | 의미 | 특이사항 |
    |---|---| --- |
    |@Entity| 실제 DB의 Table에 해당되는 Class| Table Name이 snakeCase이여도 class는 CamelCase로 작성 한다.|
    |@Column()| 실제 Table의 Column에 해당되는 변수 | 기본적으로 CamelCase로 작성하지만 특이한 column이 있는 경우 다음과 같이 맵핑이 가능하다. @Column(name = "ACCOUNT")|
    |@Enumerated(EnumType.STRING)| ENUM Class 맵핑 | ENUM에 없는 값이 들어 오는 경우 에러 발생 |
    |@OneToMany()| 1:N의 연관관계 지정 fetch type default lazy | @OneToMany(mappedBy = "student" mappedBy에는 N에 해당되는 Class의 변수명을 할당 |
    |@ManyToOne| N:1의 연관관계 지정 | OneToMany 쪽에서 관계 설정을 하였음으로, 추가 설정을 하지 않아도 동작 한다. |
    |@Id| index 에 해당되는 column (변수) 에 지정 | 보통 auto increment, primary key 에 지정 |
    |@GeneratedValue| auto increment의 방식에 대해서 지정 | mySQL의 경우 strategy = GenerationType.IDENTITY (데이터베이스 의존) 사용 |
    |@CreatedDate| 생성일자 시간 자동 주입 | 생성된 시간이 들어 간다 |
    |@CreatedBy| 생성한 사람 또는 서버 자동 주입 | 위에서 생성한 AuditorAware 을 통하여 getCurrentAuditor() 로 변경 가능 |
    |@LastModifiedDate| 데이터의 생성시, 수정시 시간 자동 주입 | 수정된 시간이 들어 간다 |
    |@LastModifiedBy| 수정한 사람 또는 서버 자동 주입 | 위에서 생성한 AuditorAware 을 통하여 getCurrentAuditor() 로 변경 가능 |
    <br>
    
4. Repository
    com.fastcampus.backoffice.respository.**
    <br>
    
    | annotation | 의미 | 특이사항 |
    |---|---| --- |
    |@Repository|Spring 에서 repository에 해당되는 interface에 설정하는 annotation | extends JpaRepository<알맞은 Entity, Entity의 @Id의 Type> | 
    <br>

5. Lombok
    <br>
   
    | annotation | 의미 | 특이사항 |
    |---|---| --- |
    |@NoArgsConstructor| 매개변수가 없는 기본 생성자 사용 ||
    |@AllArgsConstructor| 모든 매개변수의 생성자 사용 ||
    |@RequiredArgsConstructor|초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성| 주로 의존성 주입(Dependency Injection) 편의성을 위해서 사용 |
    |@Builder| 빌더 패턴을 사용할때 사용 ||
    |@Accessors(chain = true)| 체이닝 패턴을 사용할때 사용 ||
    |@Getter| Getter 메소드 생성 ||
    |@Setter| Setter 메소드 생성 ||
    |@Data| @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor 모두 포함 | JPA 에서 OneToMany, ManyToOne 같은 부분에 사용하면 toString()에서 문제 발생 @Exclude를 사용해서 제외 하거나 다른 방법으로 설정|
    <br>    
        
 