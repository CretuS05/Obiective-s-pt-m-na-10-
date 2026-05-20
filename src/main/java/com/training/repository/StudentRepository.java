package com.training.repository;

import com.training.entity.Student;
import com.training.entity.StatusStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
    List<Student> findByStatus(StatusStudent status);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT s FROM Student s WHERE s.status = com.training.entity.StatusStudent.ACTIV ORDER BY s.nume ASC")
    List<Student> findAllActivi();
    @Query("SELECT COUNT(s) FROM Student s WHERE s.status = ?1")
    long countByStatus(StatusStudent status);
}
