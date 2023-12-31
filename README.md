# CourseManagme


Welcome to the Course Management System! This project is developed using Spring Boot and provides a REST API for managing courses and professors.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine:

   ```sh
   git clone https://github.com/iiiimasal/CourseManagment.git

   
2.Navigate to the project directory:

cd course-management-system

3.Build the project using Maven:

mvn clean install

## Run Spring Boot application

mvn spring-boot:run

The application will start and you can access the API at http://localhost:8080.

## Dependencies

### PostgreSQL

If you want to use PostgreSQL as your database, you'll need to include the following dependency in your project's `pom.xml` file:

```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
xml
```
### MySQL

Alternatively, if you prefer to use MySQL, add the following dependency to your project's `pom.xml` file:

```xml
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>

xml
```


This project uses the following major dependencies:
Spring Boot
Spring Data JPA
Spring Web
PostgreSQL


## API Endpoints
The following API endpoints are available for managing courses and professors:

GET /api/v1/courses: Get a list of all courses.

GET /api/v1/courses/{courseId}: Get details of a specific course by ID.

POST /api/v1/courses: Create a new course.

PUT /api/v1/courses/{courseId}: Update details of a course.

DELETE /api/v1/courses/{courseId}: Delete a course.

GET /api/v1/professors: Get a list of all professors.

GET /api/v1/professors/{professorId}: Get details of a specific professor by ID.

POST /api/v1/professors: Create a new professor.

PUT /api/v1/professors/{professorId}: Update details of a professor.

DELETE /api/v1/professors/{professorId}: Delete a professor.

Please refer to the API documentation or the source code for more details about request and response formats.


## Database
The project uses a PostgreSQL database to store course and professor information. You can configure the database connection in the application.properties file.




