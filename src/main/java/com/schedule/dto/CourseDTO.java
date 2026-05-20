package com.schedule.dto;

import com.schedule.entity.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String code;
    private Long teacherId;
    private String teacherName;
    private Integer capacity;
    private Integer enrolledStudents;
    private String schedule;
    private String location;
    private CourseStatus status;
}
