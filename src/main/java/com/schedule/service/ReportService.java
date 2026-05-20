package com.schedule.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.schedule.entity.Course;
import com.schedule.entity.Enrollment;
import com.schedule.entity.User;
import com.schedule.entity.UserRole;
import com.schedule.repository.CourseRepository;
import com.schedule.repository.EnrollmentRepository;
import com.schedule.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService {
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    public byte[] generateCoursesReport() throws Exception {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Courses Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        String[] headers = {"Course ID", "Title", "Code", "Teacher", "Enrolled Students"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            table.addCell(course.getId().toString());
            table.addCell(course.getTitle());
            table.addCell(course.getCode());
            table.addCell(course.getTeacher().getFirstName() + " " + course.getTeacher().getLastName());
            table.addCell(course.getEnrolledStudents().toString());
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    public byte[] generateEnrollmentsReport() throws Exception {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Enrollments Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        String[] headers = {"Student", "Course", "Status", "Grade", "Enrolled Date"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        List<Enrollment> enrollments = enrollmentRepository.findAll();
        for (Enrollment enrollment : enrollments) {
            table.addCell(enrollment.getStudent().getFirstName() + " " + enrollment.getStudent().getLastName());
            table.addCell(enrollment.getCourse().getTitle());
            table.addCell(enrollment.getStatus().toString());
            table.addCell(enrollment.getGrade().toString());
            table.addCell(enrollment.getEnrolledAt().toString());
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }

    public byte[] generateStudentsReport() throws Exception {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Students Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);

        String[] headers = {"Student ID", "First Name", "Last Name", "Email", "Enrollment Count"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        List<User> students = userRepository.findByRole(UserRole.STUDENT);
        for (User student : students) {
            long enrollmentCount = enrollmentRepository.findByStudent(student).size();
            table.addCell(student.getId().toString());
            table.addCell(student.getFirstName());
            table.addCell(student.getLastName());
            table.addCell(student.getEmail());
            table.addCell(String.valueOf(enrollmentCount));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }
}
