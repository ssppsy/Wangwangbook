# WangwangBook

## Project Description

**WangwangBook** is a Spring Boot-based backend application designed to support a book recommendation and tracking platform. It connects users with books through category selection, allowing users to register, log in, and receive personalized recommendations based on their selected genres. The system also enables interaction with book details and supports category management and book uploading functionalities.

## Setup Instructions

### Prerequisites

- JDK 17+
- Gradle (or use included `gradlew`)
- MySQL or any other configured database
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps

1. **Clone the project**:
   ```bash
   git clone https://github.com/ssppsy/Wangwangbook.git
   cd WangwangBook
   ```

2. **Configure your database connection** in `src/main/resources/application.yml` or `application.properties`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/wangwangbook
   ```

3. **Run the application**:
   ```bash
   ./gradlew bootRun
   ```

4. **Access the backend API**:

- **Base URL**:  
  - For local access on your own machine: `http://localhost:8080`  
  - For access from other devices on the same network: `http://10.30.42.214:8080`  
    Make sure your firewall allows access to port 8080.

- **API documentation** (if available):  
  - Swagger UI: `http://10.30.42.214:8080/swagger-ui.html`  
  - Or: `http://10.30.42.214:8080/doc.html`

## Features and Functionality

- User registration and login
- Book listing and search
- Genre-based recommendation
- Upload and manage books
- Category management
- RESTful API design

## Known Bugs / Limitations

### Bugs
- User login/logout status may not persist across sessions under certain edge cases.

### Limitations
- Genre-based book recommendation depends on the availability of books in the selected genre.  
  If a user selects a genre that does not exist in the database, the system cannot return matching recommendations.
- **Temporary solution**: fallback to a default list of recommended books when genre-matched results are not found.
- No frontend interface is included in this project; it only provides backend API endpoints.
