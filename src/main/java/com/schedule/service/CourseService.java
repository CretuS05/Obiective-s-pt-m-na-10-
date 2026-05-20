package com.schedule.service;

import com.schedule.dto.CourseDTO;
import com.schedule.entity.Course;
import com.schedule.entity.CourseStatus;
import com.schedule.entity.User;
import com.schedule.repository.CourseRepository;
import com.schedule.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseDTO createCourse(CourseDTO courseDTO) {
        User teacher = userRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCode(courseDTO.getCode());
        course.setTeacher(teacher);
        course.setCapacity(courseDTO.getCapacity());
        course.setSchedule(courseDTO.getSchedule());
        course.setLocation(courseDTO.getLocation());
        course.setStatus(CourseStatus.ACTIVE);

        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCode(courseDTO.getCode());
        course.setCapacity(courseDTO.getCapacity());
        course.setSchedule(courseDTO.getSchedule());
        course.setLocation(courseDTO.getLocation());
        course.setUpdatedAt(LocalDateTime.now());

        Course updatedCourse = courseRepository.save(course);
        return convertToDTO(updatedCourse);
    }

    public CourseDTO getCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return convertToDTO(course);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getActiveCoures() {
        return courseRepository.findByStatus(CourseStatus.ACTIVE).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getCoursesByTeacher(Long teacherId) {
        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return courseRepository.findByTeacher(teacher).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO convertToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setCode(course.getCode());
        dto.setTeacherId(course.getTeacher().getId());
        dto.setTeacherName(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
        dto.setCapacity(course.getCapacity());
        dto.setEnrolledStudents(course.getEnrolledStudents());
        dto.setSchedule(course.getSchedule());
        dto.setLocation(course.getLocation());
        dto.setStatus(course.getStatus());
        return dto;
    }
}
