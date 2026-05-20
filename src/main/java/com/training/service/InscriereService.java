package com.training.service;

import com.training.dto.InscriereDTO;
import com.training.entity.Inscriere;
import com.training.entity.StatusInscriere;
import com.training.entity.Student;
import com.training.entity.Curs;
import com.training.repository.InscriereRepository;
import com.training.repository.StudentRepository;
import com.training.repository.CursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InscriereService {
    private final InscriereRepository inscriereRepository;
    private final StudentRepository studentRepository;
    private final CursRepository cursRepository;

    public InscriereDTO inscriudeStudent(Long studentId, Long cursId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student nu gasit"));
        Curs curs = cursRepository.findById(cursId)
                .orElseThrow(() -> new RuntimeException("Curs nu gasit"));

        if (curs.getLocuriOcupate() >= curs.getLocuri()) {
            throw new RuntimeException("Cursul este plin");
        }

        if (inscriereRepository.findByStudentAndCurs(student, curs).isPresent()) {
            throw new RuntimeException("Studentul este deja inscris la acest curs");
        }

        Inscriere inscriere = new Inscriere();
        inscriere.setStudent(student);
        inscriere.setCurs(curs);
        inscriere.setStatus(StatusInscriere.ACTIVA);
        inscriere.setNota(0.0);

        curs.setLocuriOcupate(curs.getLocuriOcupate() + 1);
        cursRepository.save(curs);

        Inscriere savedInscriere = inscriereRepository.save(inscriere);
        return convertToDTO(savedInscriere);
    }

    public void desinscriereStudent(Long inscriereId) {
        Inscriere inscriere = inscriereRepository.findById(inscriereId)
                .orElseThrow(() -> new RuntimeException("Inscriere nu gasita"));
        
        Curs curs = inscriere.getCurs();
        curs.setLocuriOcupate(Math.max(0, curs.getLocuriOcupate() - 1));
        cursRepository.save(curs);
        
        inscriereRepository.deleteById(inscriereId);
    }

    public InscriereDTO updateInscriere(Long id, InscriereDTO inscriereDTO) {
        Inscriere inscriere = inscriereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscriere nu gasita"));
        
        inscriere.setNota(inscriereDTO.getNota());
        inscriere.setProcent_prezenta(inscriereDTO.getProcent_prezenta());
        inscriere.setStatus(inscriereDTO.getStatus());
        inscriere.setUpdatedAt(LocalDateTime.now());
        
        Inscriere updated = inscriereRepository.save(inscriere);
        return convertToDTO(updated);
    }

    public List<InscriereDTO> getStudentInscrierii(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student nu gasit"));
        return inscriereRepository.findByStudent(student).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<InscriereDTO> getCursInscrierii(Long cursId) {
        Curs curs = cursRepository.findById(cursId)
                .orElseThrow(() -> new RuntimeException("Curs nu gasit"));
        return inscriereRepository.findByCurs(curs).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private InscriereDTO convertToDTO(Inscriere inscriere) {
        InscriereDTO dto = new InscriereDTO();
        dto.setId(inscriere.getId());
        dto.setStudentId(inscriere.getStudent().getId());
        dto.setStudentNume(inscriere.getStudent().getNume() + " " + inscriere.getStudent().getPrenume());
        dto.setCursId(inscriere.getCurs().getId());
        dto.setCursTitlu(inscriere.getCurs().getTitlu());
        dto.setStatus(inscriere.getStatus());
        dto.setNota(inscriere.getNota());
        dto.setDataInscriere(inscriere.getDataInscriere());
        dto.setProcent_prezenta(inscriere.getProcent_prezenta());
        return dto;
    }
}
