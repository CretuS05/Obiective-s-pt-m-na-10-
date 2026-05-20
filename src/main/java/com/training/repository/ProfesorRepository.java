package com.training.repository;

import com.training.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByUsername(String username);
    Optional<Profesor> findByEmail(String email);
    List<Profesor> findByActiv(Boolean activ);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT p FROM Profesor p WHERE p.activ = true ORDER BY p.nume ASC")
    List<Profesor> findAllActivi();
}
