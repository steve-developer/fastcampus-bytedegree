package com.fastcampus.backoffice.respository;

import com.fastcampus.backoffice.entity.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
}
