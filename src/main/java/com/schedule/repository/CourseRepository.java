package com.schedule.repository;

import com.schedule.entity.Course;
import com.schedule.entity.CourseStatus;
import com.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStatus(CourseStatus status);
    List<Course> findByTeacher(User teacher);
    List<Course> findByCode(String code);
}
