package com.training.repository;

import com.training.entity.Curs;
import com.training.entity.StatusCurs;
import com.training.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursRepository extends JpaRepository<Curs, Long> {
    List<Curs> findByStatus(StatusCurs status);
    List<Curs> findByProfesor(Profesor profesor);
    List<Curs> findByCod(String cod);
    @Query("SELECT c FROM Curs c WHERE c.status = com.training.entity.StatusCurs.ACTIV ORDER BY c.titlu ASC")
    List<Curs> findAllActivi();
    @Query("SELECT COUNT(c) FROM Curs c WHERE c.status = ?1")
    long countByStatus(StatusCurs status);
    @Query("SELECT c FROM Curs c WHERE LOWER(c.titlu) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(c.descriere) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Curs> searchByTitluOrDescriere(String keyword);
}
