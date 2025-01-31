# Cloud Vendor Api
This is a Rest API for managing cloud vendors information, contains the creation, retrieve, updating all information or partially and deleting information. 
It uses **Spring Boot** and **Spring Data with Hibernate** to establish connection with an **H2 in-memory database** for rapid development and testing. Apart from that, a microservice approach was done for the service level of this project and, also contains **Junit5 and Mockito** for test coverage purposes

## Features
- Create a new cloud vendor information based on the body of the incoming request. Persist the information of new cloud vendor in the database.
- Retrieve all cloud vendors information that exists in the database.
- Retrieve a specific cloud vendor information based on the id of the cloud vendor.
- Retrieve a list of cloud vendors information based on the name of the cloud vendor, assuming that could exist more than one cloud vendor with the same name in the database.
- Delete the cloud vendor information based on the id of the cloud vendor.
- Update all the cloud vendor information based on the id of the cloud vendor.
- Update partially the cloud vendor information based on the id of the cloud vendor.

## Technologies Used

- **Java 17**: Programming language
- **Spring Boot**: Back-end framework.
- **Spring Data JPA with Hibernate**: For database interaction.
- **H2 Database**: In-memory database for runtime and testing execution.
- **Postman**: For testing the API endpoints.

## Pre-requisites

To run this project, you'll need: 
- Java 17 or later
- Maven (for dependency management)
- Postman (for testing the API endpoints using collections)
- Git (for cloning the project files and resources)

## Instalation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/jfermedi/cloudvendor-api
   cd your-repo-name

2. Configure the database connection(optional):

    By default this project uses the in-memory H2 database.
   All the setup for the database is located in the **src/main/java/resources/application.yaml**

3. Build and run the project:
   
   The build is needed only for the first time you're setting up the project on your local environment. Simply do a *mvn build* , or if you want to add any other dependency, remember to add it on the pom.xml and execute the build again.
   To run this project it can be done by going to the SpringBoot main method (located in the **src/main/java/com/cloudvendor/demo_API/DemoApiApplication.java**) and run as a **SpringBoot Application**.

4. Access the API:

- **Base URL**: http://localhost:8080/cloudvendor
- **H2 Console**: http://localhost:8080/h2-console
- Use JDBC URL: jdbc:h2:mem:vendors-db
- Username: root
- Password: root

## API Endpoints

| HTTP Method | Endpoint                      | Description                           | Request Parameters                     | Response Example                  |
|-------------|-------------------------------|---------------------------------------|----------------------------------------|-----------------------------------|
| `GET`       | `/cloudvendor/allvendors`     | Retrieve all cloud vendors information | None                                  | `[{"vendorId": 1,"vendorName": "Vendor1","vendorPhoneNumber": "xxxx-xxxx","vendorAddress": "Address 1"}, ...]` |
| `GET`       | `/cloudvendor/{vendorId}`     | Retrieve a specific cloud vendor information based on Id | `vendorId` (String) | `{"vendorId": 1,"vendorName": "Vendor1","vendorPhoneNumber": "xxxx-xxxx","vendorAddress": "Address 1"}` |
| `GET`        | `/cloudvendor/{vendorName}`   | Retrieve all cloud vendors information based on Vendor Name | `vendorName` (String) | `[{"vendorId": 1,"vendorName": "Vendor1","vendorPhoneNumber": "xxxx-xxxx","vendorAddress": "Address 1"}, ...]` |
| `POST`       | `/cloudvendor/createvendor`   | Creates a new cloud vendor an save it on database | `{"vendorName": String,"vendorPhoneNumber": String,"vendorAddress": String}` | `Cloud Vendor created` |
| `DELETE`     | `cloudvendor/deletevendor/{vendorId}` | Deletes a cloud vendor information based on Id | `vendorId` (String) | `Cloud Vendor Deleted` |
| `PUT`        | `cloudvendor/updatevendor/{vendorId}` | Updates a cloud vendor information based on Id |   `vendorId` (String), `{"vendorName": String,"vendorPhoneNumber": String,"vendorAddress": String}` | `Cloud Vendor updated` |
| `PATCH`      | `cloudvendor/updatepart/{vendorId}` | Updates partially a cloud vendor information based on Id | `vendorId`, `fieldToBeUpdated` (String) | `Cloud Vendor partial updated` | 
