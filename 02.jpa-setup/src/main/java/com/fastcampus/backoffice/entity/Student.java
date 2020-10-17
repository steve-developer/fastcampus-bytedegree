package com.fastcampus.backoffice.entity;

import com.fastcampus.backoffice.model.enumclass.StudentStatus;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Student")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student extends BaseEntity{

    @Column(name = "account")
    private String account;                     // 수강생 계정

    private String password;                    // 수강생 계정 비밀번호

    @Enumerated(EnumType.STRING)
    private StudentStatus status;               // 수강생 상태

    private String email;                       // 수강생 이메일주소

    private String phoneNumber;                 // 수강생 전화번호

    private LocalDateTime registeredAt;         // 수강생 등록일자

    private LocalDateTime unregisteredAt;       // 수강생 해지일자

    @OneToMany(mappedBy = "student")
    private List<ApplyCourse> applyCourseList;  // 수강 강좌 목록

}
