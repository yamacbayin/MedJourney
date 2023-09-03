# MedJourney - Your Medical Tourism Companion

Welcome to MedJourney, your comprehensive medical tourism application. This project was developed as part of the Allianz GeleceğimAll Program's Java homework. Please note that the project's deadline was shortened due to a job offer from Allianz Turkiye, so while it serves as a functional prototype, it may not be fully polished.

## Purpose

MedJourney aims to simplify medical tourism for patients seeking medical services abroad. It offers a platform where patients can discover healthcare providers, schedule appointments, and manage their medical journey conveniently.

# Abstraction at Its Best

MedJourney employs the power of abstraction to build a scalable and maintainable codebase. Here's an overview of how abstraction is utilized in the project:

### BaseController

In the `BaseController` class, abstraction is evident in its generic structure. This abstract class serves as a foundation for various controllers in the project. It abstracts common CRUD (Create, Read, Update, Delete) operations, making it possible to define a single set of methods for multiple entities.

For example:
- The `getAll` method abstracts the retrieval of paginated data, making it flexible for different entities.
- The `save` method abstracts the process of saving entities.
- The `update` method abstracts updating entities based on their UUID.
- Abstraction allows controllers like `DoctorController`, `HospitalController`, and `AppointmentController` to reuse these common methods without duplication.

### BaseResponseDTO

The `BaseResponseDTO` class abstracts the common attributes shared by response DTOs (Data Transfer Objects). It includes fields like `id`, `uuid`, `creationDate`, and `lastModifiedDate`, providing a consistent structure for response objects.

### BaseService

In the `BaseService` class, abstraction is evident in the generic types used for Entity, ResponseDTO, RequestDTO, Repository, Mapper, and Specification. This abstract class forms the core of the application's business logic.

For example:
- The `getEntityByUuid` method abstracts the retrieval of an entity by its UUID, providing a consistent way to handle such operations across different entities.
- The `getAll` method abstracts the retrieval of paginated data with sorting and filtering capabilities, simplifying data retrieval for various entities.
- Abstraction allows for a common service structure for entities like `DoctorEntity`, `HospitalEntity`, and `AppointmentEntity`, promoting code reusability and maintainability.

### BaseSpecification

The `BaseSpecification` class abstracts the creation of JPA specifications for querying data from the database. It provides a flexible and reusable way to define filtering criteria for entities.

For example:
- The `toPredicate` method abstracts the creation of predicates based on filtering criteria, making it easy to filter data based on various conditions.
- Abstraction allows for consistent and efficient data querying across different entities without duplicating complex query logic.

### IBaseMapper

The `IBaseMapper` interface abstracts the mapping between entities and response DTOs. It defines methods for converting request DTOs to entities, entities to response DTOs, and more.

For example:
- The `requestDtoToEntity` method abstracts the conversion of request DTOs to entities, providing a standard way to create entities from incoming data.
- The `entityToResponseDto` method abstracts the transformation of entities into response DTOs, ensuring a consistent structure for API responses.
- Abstraction simplifies the mapping process for different entities, reducing repetitive code.

In summary, abstraction is a key design principle in MedJourney, promoting code consistency, reusability, and maintainability across various components of the application. It simplifies complex operations, making the codebase more organized and efficient.
## Security First

MedJourney takes security seriously. A dedicated security layer has been implemented to protect sensitive data and ensure user authentication and authorization.

- User roles (e.g., admin, user) define access rights.
- JWT (JSON Web Tokens) are used for secure authentication.
- Whitelisting and role-based access control restrict endpoints to authorized users.

## Getting Started

To get started with MedJourney, follow these steps:

1. Clone this repository to your local machine.
2. Set up your development environment with the required tools (Java, Spring Boot, PostgreSQL, etc.).
3. Configure the database connection in application.properties.
4. Build and run the application.
5. Access the API documentation via Swagger at `/swagger-ui.html`.
6. Use the provided endpoints to explore and interact with the application.

## Contribution

Feel free to contribute to the development of MedJourney. We welcome enhancements, bug fixes, and new features. Please open an issue or submit a pull request to get involved.

## Contact

For inquiries or support, you can reach out to me at yamacbayin@gmail.com.

Thank you for exploring MedJourney, and we hope it serves as a valuable resource for simplifying medical tourism.