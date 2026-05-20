package com.training.repository;

import com.training.entity.Inscriere;
import com.training.entity.StatusInscriere;
import com.training.entity.Student;
import com.training.entity.Curs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriereRepository extends JpaRepository<Inscriere, Long> {
    List<Inscriere> findByStudent(Student student);
    List<Inscriere> findByCurs(Curs curs);
    List<Inscriere> findByStatus(StatusInscriere status);
    Optional<Inscriere> findByStudentAndCurs(Student student, Curs curs);
    @Query("SELECT COUNT(i) FROM Inscriere i WHERE i.curs = ?1 AND i.status != com.training.entity.StatusInscriere.ANULATA")
    long countByursActive(Curs curs);
    @Query("SELECT COUNT(i) FROM Inscriere i WHERE i.status = ?1")
    long countByStatus(StatusInscriere status);
}
