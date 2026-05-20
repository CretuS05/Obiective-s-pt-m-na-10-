package com.schedule.repository;

import com.schedule.entity.Enrollment;
import com.schedule.entity.Course;
import com.schedule.entity.User;
import com.schedule.entity.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    List<Enrollment> findByCourse(Course course);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    Optional<Enrollment> findByStudentAndCourse(User student, Course course);
}
