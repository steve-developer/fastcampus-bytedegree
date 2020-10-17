package com.fastcampus.backoffice.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Getter
@Setter
public class ApplyCourseDetail extends BaseEntity{

    @ManyToOne
    private ApplyCourse applyCourse;        // 수강 강의
}
