package com.fastcampus.backoffice.respository;

import com.fastcampus.backoffice.entity.ApplyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyCourseRepository extends JpaRepository<ApplyCourse, Long> {
}
