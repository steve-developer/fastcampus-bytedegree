package com.fastcampus.backoffice.respository;

import com.fastcampus.backoffice.entity.ApplyCourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyCourseDetailRepository extends JpaRepository<ApplyCourseDetail, Long> {
}
