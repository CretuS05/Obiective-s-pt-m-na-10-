package com.training.repository;

import com.training.entity.ProgramCurs;
import com.training.entity.Curs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramCursRepository extends JpaRepository<ProgramCurs, Long> {
    List<ProgramCurs> findByCurs(Curs curs);
}
