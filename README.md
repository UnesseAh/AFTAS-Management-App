## Spring Boot REST API Project
This is a Spring Boot project that provides a RESTfull API for [AFTAS-Management-App-Spring-Boot]. The project utilizes OpenAPI and integrates with Swagger UI for easy documentation and testing of the API endpoints.

### Prerequisites
Before running the project, ensure that you have the following:

* Java Development Kit (JDK) installed (version 8 or higher)
* Maven installed (version 3.6 or higher)

## Getting Started
To get started with the project, follow these steps:

1-Clone the repository to your local machine:

```
git clone https://github.com/UnesseAh/AFTAS-Management-App-Spring-Boot.git
```

Navigate to the project directory:

```shell
cd AFTAS
```

Build the project using Maven:

```shell
mvn clean install
```

Run the project:

```shell
mvn spring-boot:run
````
The application will start running on `http://localhost:8080`


## Swagger UI
Swagger UI is integrated into the project, providing an interactive documentation and testing interface for the API endpoints. You can access Swagger UI by visiting the following URL in your web browser:

```
http://localhost:8080/swagger-ui/index.html
```

## API Endpoints
The following are the available API endpoints provided by this project:

First Header  | Second Header
------------- | -------------
[PUT] /api/v1/levels/{id}  | Updates the level with the specified ID.
[DELETE] /api/v1/levels/{id}  | Deletes the level with the specified ID.
[PUT] /api/v1/fishes/{id}  | Updates the fish with the specified ID.
[DELETE] /api/v1/fishes/{id}  | Deletes the fish with the specified ID.
[POST] /api/v1/rankings/{competition}  | Generates the ranking for the specified competition.
[POST] /api/v1/rankings/{competition}/podium  | Shows the podium for the specified competition.
[POST] /api/v1/rankings/register  | Registers a member in a competition.
[GET] /api/v1/members  | Retrieves all members.
[POST] /api/v1/members  | Creates a new member.
[GET] /api/v1/levels  | Retrieves all levels.
[POST] /api/v1/levels  | Creates a new level.
[POST] /api/v1/huntings  | Creates a new hunting.
[GET] /api/v1/fishes  | Retrieves all fishes.
[POST] /api/v1/fishes  | Creates a new fish.
[GET] /api/v1/competitions  | Retrieves all competitions.
[POST] /api/v1/competitions  | Creates a new competition.
[GET] /api/v1/members/search/{search_key}  | Searches for a member by the specified search key.
[GET] /api/v1/competitions/{code}  | Finds a competition by the specified code

Please refer to the Swagger UI documentation for detailed information and usage examples of each endpoint.