PROJECT JPA TEST RENEWAL
====================

소개
----
이전 수업 내용 [03. jpa-test](https://github.com/StudyIsEasy/fastcampus-studyadmin/tree/feature/03-jpa-test) 의 수정 버전 입니다.
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

#### 변경점 기존 강의대로 진행 해도 괜찮지만 통합테스트에 사용하는 설정에서 단위 테스트로 변경 하였습니다.
* 오타수정 ( 변경 필수 )
```
기존 
package com.fastcampus.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing  // <<< 해당 부분 삭제
@SpringBootApplication
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
```

```
com.fastcampus.admin.config

// @Configurable // << 오타 부분
@Configuration  // 수정 Configurable -> Configuration
@EnableJpaAuditing
public class JpaConfig {
}
```


#### JPA 단위 테스트 주요 내용 <br>

* 스프링 테스트 Default Auto-configuration : [Link](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-test-auto-configuration.html#test-auto-configuration)

* 스프링 부트 단위 테스트
    - 스프링에서 단위테스트는 여러가지가 있지만 그중에 대표적인것을 보면 다음과 같다. 너무 많아서 현 프로젝트에서 사용한 것만 설명하고 나머지는 [Link](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing) 참고해 주세요.<br>
    단위테스트는 보통 Method 레벨로 테스트가 이뤄지며, 하나의 기능이 잘 동작하는지를 검사 하는 테스트 이다. 그러므로 불필요한 Bean들을 로드할 필요가 없으며, 모두 로드 하는 경우 테스트의 시간만 길어지는 단점이 있다.
       
        | annotation | 의미 | Default Auto-configuration |
        |---|---| --- |
        |@WebMvcTest| MVC를 테스트 하기 위한 어노테이션, 간단한 Controller 에 대해서 테스트가 가능하다.| [Imported auto-configuration 참고](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-test-auto-configuration.html#test-auto-configuration)  |
        |@DataJpaTest| JPA 테스트 하기 위한 어노테이션, JPA관련만 로드 된다. |[Imported auto-configuration 참고](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-test-auto-configuration.html#test-auto-configuration)  |
        |@RestClientTest| REST Client 테스트 용도, RestTemplate과 같은 http client사용시 Mock Server를 만드는 용도 |[Imported auto-configuration 참고](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-test-auto-configuration.html#test-auto-configuration)  |

        
* 스프링 부트 통합 테스트
    - 스프링에서 통합 테스트를 위해서는 다음의 Annotation을 사용한다.<br>
    주로 controller 부터 service, repository, external library 등 모든 부분을 테스트하며, 스프링의 모든 Bean을 로드하여 사용하므로 테스트의 시간이 길다.
    <br>
        
    | annotation | 의미 | 특이사항 |
    |---|---| --- |
    |@SpringBootTest| 스프링의 실행 부터 모든 bean을 로드하여, 처음부터 끝까지 모두 테스트 가능 | |
    <br/>
       
* 그외 사용하는 Annotation
    <br>
    
    | annotation | 의미 | 특이사항 |
    |---|---| --- |
    |@Import| 단위테스트를 할때, 별도로 만든 Bean, Component 등 필요한 Bean Load | |
    |@AutoConfigureTestDatabase| 테스트시 실제 DB를 사용하거나 Memory DB를 사용할때 사용 | ex) @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) |
    |@DisplayName| 테스트의 이름을 지정 | |
    |@Test| 테스트할 Method 지정 | |
    |@Transactional| 테스트가 끝나면 해당 DB의 내용을 ROLL BACK할때 사용한다. | DB의 AUTO Increment index의 숫자는 계속 증가 한다. |
    |@TestConfiguration| 테스트에서 사용할 설정을 할때 class 에 적용 한다. ||

* Junit5
    - org.springframework.util Assert 대신에 org.junit.jupiter.api 에 있는 Assertions 사용    
    
#### Example
```
@SpringBootTest
@DataJpaTest                                                                    // JPA 테스트 관련 컴포넌트만 Import
@Import({JpaConfig.class, AdminAuditorAware.class})                             // JPA 관련 Class Import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 db 사용
@DisplayName("학생 정보 Repository 테스트")
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;                                // Student Repository 테스트

    @Test                   // JUnit Test Annotation
    @Transactional          // 데이터 테스트 후 롤백 ( auto increment index 는 롤백 안됨)
    @DisplayName("학생 정보 생성 테스트")
    public void create(){
        Student student = Student.builder()
                .account("student01")
                .password("student01")
                .status(StudentStatus.REGISTERED)
                .email("student01@gmail.com")
                .phoneNumber("010-1111-1111")
                .registeredAt(LocalDateTime.now())
                .build();

        Student newStudent = studentRepository.save(student);
        Assertions.assertNotNull(newStudent);           // save 된 경우 저장된 객체를 반환
        Assertions.assertNotNull(newStudent.getId());   // JPA save 시 id를 자동으로  반환
    }
}
```
