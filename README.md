# School Schedule Management Application

A comprehensive online courses and school schedule management system built with Spring Boot, MySQL, and React.

## Features

✅ **User Management**
- Role-based access control (Admin, Teacher, Student)
- User registration and authentication
- JWT-based security

✅ **Course Management**
- Create, read, update, delete courses
- Assign teachers to courses
- Manage course capacity and enrollment
- Track course status

✅ **Enrollment Management**
- Student enrollment in courses
- Enrollment status tracking
- Grade management
- Course availability checks

✅ **Schedule Management**
- Create course schedules
- Day-wise schedule organization
- Location tracking
- Time slot management

✅ **Reports & Analytics**
- PDF reports for courses
- Enrollment statistics
- Student reports
- Exportable data

✅ **Responsive UI**
- Mobile-friendly design
- Bootstrap 5 styling
- Intuitive navigation
- Real-time updates

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.0
- **Database**: MySQL 8.0
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven
- **ORM**: Hibernate/JPA
- **PDF Generation**: iText

### Frontend
- **Templates**: Thymeleaf
- **Styling**: Bootstrap 5
- **JavaScript**: Vanilla JS + jQuery
- **Icons**: Font Awesome 6

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- Node.js 14+ (optional, for React frontend)

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/CretuS05/Obiective-s-pt-m-na-10-.git
cd Obiective-s-pt-m-na-10-
```

### 2. Database Setup

Create a MySQL database:
```sql
CREATE DATABASE school_schedule_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/school_schedule_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Update JWT Secret

Change the JWT secret in `application.properties`:
```properties
jwt.secret=your_very_secret_key_with_at_least_32_characters
```

### 4. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/active` - Get active courses
- `GET /api/courses/{id}` - Get course details
- `POST /api/courses` - Create course (Admin/Teacher)
- `PUT /api/courses/{id}` - Update course (Admin/Teacher)
- `DELETE /api/courses/{id}` - Delete course (Admin)
- `GET /api/courses/teacher/{teacherId}` - Get courses by teacher

### Enrollments
- `POST /api/enrollments/enroll` - Enroll student
- `DELETE /api/enrollments/{enrollmentId}` - Disenroll student
- `GET /api/enrollments/student/{studentId}` - Get student enrollments
- `GET /api/enrollments/course/{courseId}` - Get course enrollments

### Users
- `GET /api/users/{id}` - Get user details
- `GET /api/users` - Get all users (Admin)
- `GET /api/users/role/{role}` - Get users by role (Admin)
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user (Admin)

### Reports
- `GET /api/reports/courses/pdf` - Download courses report
- `GET /api/reports/enrollments/pdf` - Download enrollments report
- `GET /api/reports/students/pdf` - Download students report

## Default Login Credentials

After registration, use the registered credentials to login.

## Project Structure

```
src/main/
├── java/com/schedule/
│   ├── controller/         # REST API controllers
│   ├── service/           # Business logic
│   ├── entity/            # JPA entities
│   ├── repository/        # Data access layer
│   ├── dto/              # Data transfer objects
│   ├── security/         # JWT and security config
│   └── SchoolScheduleApplication.java
├── resources/
│   ├── templates/        # Thymeleaf templates
│   ├── static/          # CSS, JS, images
│   └── application.properties
└── test/
```

## Usage Examples

### Register a New User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "role": "STUDENT"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "password": "password123"
  }'
```

### Create a Course
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "title": "Java Programming",
    "description": "Learn Java programming from basics",
    "code": "CS101",
    "teacherId": 1,
    "capacity": 30,
    "schedule": "Monday, Wednesday 10:00-11:30",
    "location": "Room 101"
  }'
```

## Security Features

- JWT token-based authentication
- Role-based access control
- Password encryption using BCrypt
- CORS configuration
- CSRF protection
- Input validation

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues.

## License

MIT License - See LICENSE file for details

## Support

For support, email support@schoolschedule.com or open an issue on GitHub.

## Future Enhancements

- Video conferencing integration
- Assignment submission and grading
- Notification system
- Mobile app (iOS/Android)
- Advanced analytics dashboard
- Payment integration
- Discussion forums
- Real-time chat

---

Made with ❤️ for educators and students