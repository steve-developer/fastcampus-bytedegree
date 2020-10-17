package com.fastcampus.backoffice.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Setter
public class ApplyCourse extends BaseEntity{

    private String status;                                      // 수강중 강의 상태

    private Float progressRate;                                 // 진행률

    private Boolean isComplete;                                 // 강의 완료 여부

    private LocalDateTime expireAt;                             // 강의 만료 일

    @OneToMany(mappedBy = "applyCourse")
    private List<ApplyCourseDetail> applyCourseDetailList;      // 수강 강의 상세 사항

    @ManyToOne
    private Student student;                                    // 수강생
}
