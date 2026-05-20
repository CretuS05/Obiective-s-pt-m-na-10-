package com.training.service;

import com.training.dto.StudentDTO;
import com.training.entity.Student;
import com.training.entity.StatusStudent;
import com.training.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByUsername(studentDTO.getUsername())) {
            throw new RuntimeException("Username deja exista");
        }
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new RuntimeException("Email deja exista");
        }

        Student student = new Student();
        student.setNume(studentDTO.getNume());
        student.setPrenume(studentDTO.getPrenume());
        student.setEmail(studentDTO.getEmail());
        student.setUsername(studentDTO.getUsername());
        student.setParola(passwordEncoder.encode(studentDTO.getUsername())); // Default parola
        student.setTelefon(studentDTO.getTelefon());
        student.setNivelelStudiu(studentDTO.getNivelelStudiu());
        student.setStatus(StatusStudent.ACTIV);

        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student nu gasit"));

        student.setNume(studentDTO.getNume());
        student.setPrenume(studentDTO.getPrenume());
        student.setTelefon(studentDTO.getTelefon());
        student.setNivelelStudiu(studentDTO.getNivelelStudiu());
        student.setStatus(studentDTO.getStatus());
        student.setUpdatedAt(LocalDateTime.now());

        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student nu gasit"));
        return convertToDTO(student);
    }

    public List<StudentDTO> getAllStudenti() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<StudentDTO> getActivStudenti() {
        return studentRepository.findAllActivi().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setNume(student.getNume());
        dto.setPrenume(student.getPrenume());
        dto.setEmail(student.getEmail());
        dto.setUsername(student.getUsername());
        dto.setTelefon(student.getTelefon());
        dto.setNivelelStudiu(student.getNivelelStudiu());
        dto.setStatus(student.getStatus());
        return dto;
    }
}
