package com.schedule.controller;

import com.schedule.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/courses/pdf")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<byte[]> generateCoursesReport() throws Exception {
        byte[] pdf = reportService.generateCoursesReport();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("courses_report.pdf")
                .build());
        
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/enrollments/pdf")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<byte[]> generateEnrollmentsReport() throws Exception {
        byte[] pdf = reportService.generateEnrollmentsReport();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("enrollments_report.pdf")
                .build());
        
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/students/pdf")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<byte[]> generateStudentsReport() throws Exception {
        byte[] pdf = reportService.generateStudentsReport();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("students_report.pdf")
                .build());
        
        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
