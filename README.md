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





