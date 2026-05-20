package com.training.service;

import com.training.dto.StatisticsDTO;
import com.training.entity.StatusStudent;
import com.training.entity.StatusCurs;
import com.training.entity.StatusInscriere;
import com.training.repository.StudentRepository;
import com.training.repository.CursRepository;
import com.training.repository.InscriereRepository;
import com.training.repository.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsService {
    private final StudentRepository studentRepository;
    private final CursRepository cursRepository;
    private final InscriereRepository inscriereRepository;
    private final ProfesorRepository profesorRepository;

    public StatisticsDTO getStatistics() {
        StatisticsDTO stats = new StatisticsDTO();
        
        stats.setTotalStudenti(studentRepository.count());
        stats.setStudentiActivi(studentRepository.countByStatus(StatusStudent.ACTIV));
        stats.setTotalCursuri(cursRepository.count());
        stats.setCursoriActivi(cursRepository.countByStatus(StatusCurs.ACTIV));
        stats.setTotalInscriieri(inscriereRepository.count());
        stats.setInscrieriActive(inscriereRepository.countByStatus(StatusInscriere.ACTIVA));
        stats.setTotalProfesori(profesorRepository.count());
        
        long locuriTotale = cursRepository.findAll().stream()
                .mapToLong(c -> c.getLocuri())
                .sum();
        long locuriOcupate = cursRepository.findAll().stream()
                .mapToLong(c -> c.getLocuriOcupate())
                .sum();
        
        if (locuriTotale > 0) {
            stats.setProcent_ocupare((locuriOcupate * 100.0) / locuriTotale);
        }
        
        return stats;
    }
}
