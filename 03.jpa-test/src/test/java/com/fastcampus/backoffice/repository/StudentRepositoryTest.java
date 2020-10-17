package com.fastcampus.backoffice.repository;

import com.fastcampus.backoffice.component.AdminAuditorAware;
import com.fastcampus.backoffice.config.JpaConfig;
import com.fastcampus.backoffice.entity.Student;
import com.fastcampus.backoffice.model.enumclass.StudentStatus;
import com.fastcampus.backoffice.respository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Test
    @Transactional
    public void read(){
        Student student = Student.builder()
                .account("student01")
                .password("student01")
                .status(StudentStatus.REGISTERED)
                .email("student01@gmail.com")
                .phoneNumber("010-1111-1111")
                .registeredAt(LocalDateTime.now())
                .build();
        Student temp = studentRepository.save(student);

        Optional<Student> newStudent = studentRepository.findById(temp.getId());
        Assert.isTrue(newStudent.isPresent(),"");
    }

    @Test
    @Transactional
    public void update(){
        Student temp = studentRepository.save(createStudent());

        Student student = studentRepository.getOne(temp.getId());
        student.setStatus(StudentStatus.UNREGISTERED);
        studentRepository.save(student);

        Optional<Student> newStudent = studentRepository.findById(student.getId());
        Assert.isTrue(newStudent.isPresent(),"");
        Assert.isTrue(newStudent.get().getStatus().equals(StudentStatus.UNREGISTERED),"");
    }

    @Test
    @Transactional
    public void delete(){
        Student student = Student.builder()
                .account("student01")
                .password("student01")
                .status(StudentStatus.REGISTERED)
                .email("student01@gmail.com")
                .phoneNumber("010-1111-1111")
                .registeredAt(LocalDateTime.now())
                .build();
        Student temp = studentRepository.save(student);
        studentRepository.delete(temp);

        Optional<Student> delete = studentRepository.findById(temp.getId());
        Assert.isTrue(!delete.isPresent(),"");
    }

    public Student createStudent(){
        return Student.builder()
                .account("student01")
                .password("student01")
                .status(StudentStatus.REGISTERED)
                .email("student01@gmail.com")
                .phoneNumber("010-1111-1111")
                .registeredAt(LocalDateTime.now())
                .build();
    }
}
