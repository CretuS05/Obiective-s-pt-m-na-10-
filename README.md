# Curse de Formare - Platform de Management

O platformă profesională și modernă pentru gestionarea cursurilor de formare online.

## 🎯 Caracteristici Principale

✅ **Gestionare Cursuri**
- Creați, editați și ștergeți cursuri
- Sistemul de prioritate (Scăzută, Normală, Înaltă, Critică)
- Tracking locuri disponibile vs ocupate
- Nivele de dificultate (Începător, Intermediar, Avansat)
- Status management (Activ, Inactiv, Arhivat, Anulat)

✅ **Management Studenți**
- Înregistrare și administrare studenți
- Urmărire status student (Activ, Inactiv, Suspendat, Absolvent)
- Contact și informații de profil
- Conectare la cursuri

✅ **Gestionare Inscriieri**
- Înscrierea studenților la cursuri
- Urmărire status inscriere
- Evaluare prin note și procent prezență
- Gestionare capacitate curs

✅ **Program Cursuri**
- Planificare cursuri pe zile și ore
- Locație și durată specificată
- Date start și final

✅ **Dashboard cu Statistici**
- Număr total studenți și profesori
- Statistici cursuri active
- Procent ocupare cursuri
- Rapoarte și exporturi

✅ **Interfață Utilizator Modernă**
- Dark theme profesional
- Design responsive
- UI intuitiv și ușor de folosit
- Animații și efecte moderne

✅ **Securitate & Autentificare**
- Login separat pentru Profesori și Studenți
- Autentificare JWT
- Control access pe rol (PROFESOR, STUDENT)
- Criptare parolă BCrypt

✅ **Limbă Română**
- Interfață completă în limba română
- Mesaje și notificări în română

## 🛠️ Tehnologii Utilizate

### Backend
- **Spring Boot 3.1** - Framework web
- **Spring Security** - Autentificare și autorizare
- **Spring Data JPA** - Accesul la date
- **MySQL 8.0** - Baza de date
- **JWT** - Autentificare token-based
- **Maven** - Build management

### Frontend
- **React** - Interfață utilizator
- **Bootstrap 5** - Styling responsive
- **Font Awesome** - Iconuri
- **Axios** - HTTP client
- **React Router** - Navigare

### Baze de Date
- **Profesori** - Informații profesori și cursuri
- **Studenti** - Date studenți și status
- **Cursuri** - Detalii cursuri și metadata
- **Inscrierii** - Relații student-curs și evaluare
- **Program Cursuri** - Orar și planificare

## 📋 Entități Database

### Profesor
- ID, Nume, Prenume
- Email, Username, Parola
- Specialitate, Telefon
- Status activ
- Cursuri asociate

### Student
- ID, Nume, Prenume
- Email, Username, Parola
- Telefon, Nivel studiu
- Status (Activ, Inactiv, Suspendat, Absolvent)
- Inscrierii asociate

### Curs
- ID, Titlu, Descriere
- Cod unic
- Profesor (FK)
- Locuri disponibile
- Nivel (Începător, Intermediar, Avansat)
- Status curs
- Prioritate
- Ore totale, Preț
- Programe și inscrierii

### Inscriere
- ID, Student (FK), Curs (FK)
- Status inscriere
- Nota finală
- Data inscriere
- Procent prezență
- Feedback

### ProgramCurs
- ID, Curs (FK)
- Ziua săptămânii
- Ora start și finală
- Locație
- Data start și finală
- Durată (minute)

## 🚀 Instalare și Configurare

### Cerințe Prealabile
- Java 17 sau mai nou
- MySQL 8.0 sau mai nou
- Maven 3.6+
- Node.js 14+ (pentru React frontend)

### Backend Setup

1. **Clonați repository-ul**
   ```bash
   git clone <repository-url>
   cd curse-formare
   ```

2. **Creați baza de date MySQL**
   ```sql
   CREATE DATABASE curse_formare_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Configurați application.properties**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/curse_formare_db
   spring.datasource.username=root
   spring.datasource.password=root
   jwt.secret=curse_formare_super_secret_key_change_in_production
   ```

4. **Build și Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

Servarul va rula pe: `http://localhost:8080`

### Frontend Setup (Optional)

1. **Creați React app**
   ```bash
   npx create-react-app frontend
   cd frontend
   ```

2. **Instalați dependențe**
   ```bash
   npm install axios react-router-dom bootstrap react-bootstrap react-icons
   ```

3. **Rulați aplicația**
   ```bash
   npm start
   ```

## 📡 API Endpoints

### Autentificare
- `POST /api/auth/login-profesor` - Login profesor
- `POST /api/auth/login-student` - Login student

### Cursuri
- `GET /api/cursuri` - Toate cursurile
- `GET /api/cursuri/activi` - Cursuri active
- `GET /api/cursuri/{id}` - Detalii curs
- `POST /api/cursuri` - Creare curs (Profesor)
- `PUT /api/cursuri/{id}` - Actualizare curs (Profesor)
- `DELETE /api/cursuri/{id}` - Ștergere curs (Profesor)
- `GET /api/cursuri/search?keyword=...` - Căutare cursuri
- `GET /api/cursuri/profesor/{id}` - Cursuri profesor

### Studenți
- `GET /api/studenti` - Toți studenii (Profesor)
- `GET /api/studenti/activi` - Studenți activi
- `GET /api/studenti/{id}` - Detalii student
- `POST /api/studenti` - Creare student (Profesor)
- `PUT /api/studenti/{id}` - Actualizare student
- `DELETE /api/studenti/{id}` - Ștergere student (Profesor)

### Inscrierii
- `POST /api/inscrierii/inscriere` - Înscrierea student (Student)
- `GET /api/inscrierii/student/{id}` - Inscrierii student
- `GET /api/inscrierii/curs/{id}` - Inscrierii curs
- `PUT /api/inscrierii/{id}` - Actualizare inscriere (Profesor)
- `DELETE /api/inscrierii/{id}` - Desinscrierea (Student)

### Statistici
- `GET /api/statistics` - Dashboard statistici

## 👥 Credențiale Teste (După Seed)

### Profesor
- Username: `profesor1`
- Parola: `profesor1`

### Student
- Username: `student1`
- Parola: `student1`

## 🎨 Design Features

- **Dark Theme Modern** - Interfață dark profesională și moderna
- **Gradient Colors** - Gradienți vizuali atractive
- **Responsive Design** - Funcționează perfect pe toate dispozitivele
- **Smooth Animations** - Animații ușoare și fluidă
- **Professional UI** - Design pentru platformă educațională

## 📊 Structura Proiect

```
curse-formare/
├── src/
│   ├── main/
│   │   ├── java/com/training/
│   │   │   ├── entity/          # JPA Entities
│   │   │   ├── repository/       # Data Access Layer
│   │   │   ├── service/          # Business Logic
│   │   │   ├── controller/       # REST Controllers
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   └── security/         # Security Config
│   │   └── resources/
│   │       ├── templates/        # Thymeleaf Templates
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔐 Securitate

- Autentificare JWT Token-based
- Criptare parolă BCrypt
- Control access pe rol (PROFESOR, STUDENT)
- CORS configurare sigură
- Input validation pe toate endpoints-urile

## 📝 Licență

MIT License - Liber de folosit în proiecte educaționale și comerciale.

## 💬 Suport

Pentru probleme sau întrebări, contactați echipa de development.

## 🚀 Îmbunătățiri Viitoare

- [ ] Integrare Zoom/Google Meet pentru clase live
- [ ] Sistem de notificări real-time
- [ ] Forum și chat între studenți și profesori
- [ ] Mobile app (iOS/Android)
- [ ] Certificare digitală la finalizare
- [ ] Evaluare și feedback automation
- [ ] Sistem de recomandări cursuri
- [ ] Integrare plăți online

---

Creat cu ❤️ pentru educație și formare profesională