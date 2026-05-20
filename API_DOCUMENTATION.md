# API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication

All protected endpoints require JWT token in Authorization header:
```
Authorization: Bearer <JWT_TOKEN>
```

## Endpoints

### Authentication Endpoints

#### Register User
```
POST /auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "password": "password123",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "STUDENT"
}

Response: 200 OK
{
  "token": "eyJhbGc...",
  "userId": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "STUDENT"
}
```

#### Login
```
POST /auth/login
Content-Type: application/json

{
  "username": "johndoe",
  "password": "password123"
}

Response: 200 OK
{
  "token": "eyJhbGc...",
  "userId": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "STUDENT"
}
```

### Course Endpoints

#### Get All Courses
```
GET /courses
Authorization: Bearer <TOKEN>

Response: 200 OK
[
  {
    "id": 1,
    "title": "Java Programming",
    "description": "Learn Java basics",
    "code": "CS101",
    "teacherId": 2,
    "teacherName": "John Teacher",
    "capacity": 30,
    "enrolledStudents": 15,
    "schedule": "Monday, Wednesday 10:00-11:30",
    "location": "Room 101",
    "status": "ACTIVE"
  }
]
```

#### Get Active Courses
```
GET /courses/active

Response: 200 OK
[]
```

#### Get Course by ID
```
GET /courses/{id}
Authorization: Bearer <TOKEN>

Response: 200 OK
{
  "id": 1,
  "title": "Java Programming",
  ...
}
```

#### Create Course
```
POST /courses
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "title": "Python Programming",
  "description": "Learn Python from scratch",
  "code": "CS102",
  "teacherId": 2,
  "capacity": 40,
  "schedule": "Tuesday, Thursday 14:00-15:30",
  "location": "Room 202"
}

Response: 201 CREATED
{
  "id": 2,
  "title": "Python Programming",
  ...
}
```

#### Update Course
```
PUT /courses/{id}
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "title": "Python Programming Advanced",
  "description": "Advanced Python concepts",
  "code": "CS102",
  "capacity": 35,
  "schedule": "Tuesday, Thursday 14:00-15:30",
  "location": "Room 202"
}

Response: 200 OK
```

#### Delete Course
```
DELETE /courses/{id}
Authorization: Bearer <TOKEN>

Response: 204 NO CONTENT
```

### Enrollment Endpoints

#### Enroll Student
```
POST /enrollments/enroll
Authorization: Bearer <TOKEN>

Query Parameters:
- studentId: Long
- courseId: Long

Response: 201 CREATED
{
  "id": 1,
  "studentId": 1,
  "studentName": "John Doe",
  "courseId": 1,
  "courseName": "Java Programming",
  "status": "ENROLLED",
  "grade": 0.0,
  "enrolledAt": "2024-05-20T10:30:00"
}
```

#### Get Student Enrollments
```
GET /enrollments/student/{studentId}
Authorization: Bearer <TOKEN>

Response: 200 OK
[]
```

#### Get Course Enrollments
```
GET /enrollments/course/{courseId}
Authorization: Bearer <TOKEN>

Response: 200 OK
[]
```

#### Disenroll Student
```
DELETE /enrollments/{enrollmentId}
Authorization: Bearer <TOKEN>

Response: 204 NO CONTENT
```

### User Endpoints

#### Get User by ID
```
GET /users/{id}
Authorization: Bearer <TOKEN>

Response: 200 OK
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "STUDENT",
  "enabled": true
}
```

#### Get All Users (Admin only)
```
GET /users
Authorization: Bearer <ADMIN_TOKEN>

Response: 200 OK
[]
```

#### Update User
```
PUT /users/{id}
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@example.com"
}

Response: 200 OK
```

#### Delete User (Admin only)
```
DELETE /users/{id}
Authorization: Bearer <ADMIN_TOKEN>

Response: 204 NO CONTENT
```

### Report Endpoints

#### Download Courses Report
```
GET /reports/courses/pdf
Authorization: Bearer <TOKEN>

Response: 200 OK
Content-Type: application/pdf
[PDF File]
```

#### Download Enrollments Report
```
GET /reports/enrollments/pdf
Authorization: Bearer <TOKEN>

Response: 200 OK
Content-Type: application/pdf
[PDF File]
```

#### Download Students Report
```
GET /reports/students/pdf
Authorization: Bearer <ADMIN_TOKEN>

Response: 200 OK
Content-Type: application/pdf
[PDF File]
```

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2024-05-20T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid input"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-05-20T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid or expired token"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2024-05-20T10:30:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied"
}
```

### 404 Not Found
```json
{
  "timestamp": "2024-05-20T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found"
}
```

### 500 Internal Server Error
```json
{
  "timestamp": "2024-05-20T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An error occurred"
}
```
