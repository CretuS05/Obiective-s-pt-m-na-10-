# Deployment Guide

## Local Development

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.6+

### Steps
1. Clone repository
2. Configure database in `application.properties`
3. Run `mvn clean install`
4. Run `mvn spring-boot:run`
5. Access at `http://localhost:8080`

## Docker Deployment

### Create Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/school-schedule-management-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
```

### Build and Run
```bash
docker build -t school-schedule:latest .
docker run -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://host:3306/school_schedule_db school-schedule:latest
```

## Production Deployment

### Environment Variables
```
SPRING_DATASOURCE_URL=jdbc:mysql://prod-host:3306/school_schedule_db
SPRING_DATASOURCE_USERNAME=db_user
SPRING_DATASOURCE_PASSWORD=db_password
JWT_SECRET=your_production_secret_key
JWT_EXPIRATION=86400000
```

### AWS EC2 Deployment
1. Launch EC2 instance (Ubuntu 20.04 LTS)
2. Install Java: `sudo apt-get install openjdk-17-jdk`
3. Install MySQL
4. Configure application properties
5. Build JAR: `mvn clean install -DskipTests`
6. Run: `java -jar target/school-schedule-management-1.0.0.jar`

### Heroku Deployment
1. Create Procfile:
```
web: java -jar target/school-schedule-management-1.0.0.jar
```
2. Deploy: `git push heroku main`

## Database Backup

### MySQL Backup
```bash
mysqldump -u root -p school_schedule_db > backup.sql
```

### MySQL Restore
```bash
mysql -u root -p school_schedule_db < backup.sql
```

## Monitoring

### Application Logs
```bash
tail -f logs/application.log
```

### Database Monitoring
```sql
SHOW PROCESSLIST;
SHOW STATUS;
```

## SSL Certificate

Configure HTTPS in `application.properties`:
```properties
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
```
