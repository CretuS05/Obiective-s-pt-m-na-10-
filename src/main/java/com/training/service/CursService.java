package com.training.service;

import com.training.dto.CursDTO;
import com.training.entity.Curs;
import com.training.entity.StatusCurs;
import com.training.entity.Profesor;
import com.training.repository.CursRepository;
import com.training.repository.ProfesorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CursService {
    private final CursRepository cursRepository;
    private final ProfesorRepository profesorRepository;

    public CursDTO createCurs(CursDTO cursDTO) {
        Profesor profesor = profesorRepository.findById(cursDTO.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor nu gasit"));

        Curs curs = new Curs();
        curs.setTitlu(cursDTO.getTitlu());
        curs.setDescriere(cursDTO.getDescriere());
        curs.setCod(cursDTO.getCod());
        curs.setProfesor(profesor);
        curs.setLocuri(cursDTO.getLocuri());
        curs.setNivel(cursDTO.getNivel());
        curs.setStatus(StatusCurs.ACTIV);
        curs.setOreTotale(cursDTO.getOreTotale());
        curs.setPret(cursDTO.getPret());

        Curs savedCurs = cursRepository.save(curs);
        return convertToDTO(savedCurs);
    }

    public CursDTO updateCurs(Long id, CursDTO cursDTO) {
        Curs curs = cursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curs nu gasit"));

        curs.setTitlu(cursDTO.getTitlu());
        curs.setDescriere(cursDTO.getDescriere());
        curs.setCod(cursDTO.getCod());
        curs.setLocuri(cursDTO.getLocuri());
        curs.setNivel(cursDTO.getNivel());
        curs.setStatus(cursDTO.getStatus());
        curs.setPrioritate(cursDTO.getPrioritate());
        curs.setOreTotale(cursDTO.getOreTotale());
        curs.setPret(cursDTO.getPret());
        curs.setUpdatedAt(LocalDateTime.now());

        Curs updatedCurs = cursRepository.save(curs);
        return convertToDTO(updatedCurs);
    }

    public CursDTO getCurs(Long id) {
        Curs curs = cursRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curs nu gasit"));
        return convertToDTO(curs);
    }

    public List<CursDTO> getAllCursuri() {
        return cursRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CursDTO> getActiveCursuri() {
        return cursRepository.findAllActivi().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CursDTO> searchCursuri(String keyword) {
        return cursRepository.searchByTitluOrDescriere(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CursDTO> getCursuriByProfesor(Long profesorId) {
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor nu gasit"));
        return cursRepository.findByProfesor(profesor).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteCurs(Long id) {
        cursRepository.deleteById(id);
    }

    private CursDTO convertToDTO(Curs curs) {
        CursDTO dto = new CursDTO();
        dto.setId(curs.getId());
        dto.setTitlu(curs.getTitlu());
        dto.setDescriere(curs.getDescriere());
        dto.setCod(curs.getCod());
        dto.setProfesorId(curs.getProfesor().getId());
        dto.setProfesorNume(curs.getProfesor().getNume() + " " + curs.getProfesor().getPrenume());
        dto.setLocuri(curs.getLocuri());
        dto.setLocuriOcupate(curs.getLocuriOcupate());
        dto.setNivel(curs.getNivel());
        dto.setStatus(curs.getStatus());
        dto.setPrioritate(curs.getPrioritate());
        dto.setOreTotale(curs.getOreTotale());
        dto.setPret(curs.getPret());
        return dto;
    }
}
