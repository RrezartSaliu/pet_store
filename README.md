# Running the project

## Prerequisites
- Docker
- Docker compose
- Java 21

## Steps to run
1. Clone the repository
   ```bash
   git clone https://github.com/RrezartSaliu/pet_store.git
   cd pet_store
   ```
2. Build the application jar:
    ```bash
    ./gradlew bootJar
    ```
    on Windows:
    ```bash
    .\gradlew.bat bootJar
    ```
3. Start the application with Docker Compose:
    ```bash
    docker compose up --build
    ```
The application will be available at `http://localhost:8080`

Endpoints:

`/api/user`:

    /create-users?userCount=INT POST
    /list-users GET
    /buy POST
`/api/pet`:

    /create-pets?petCount=INT POST
    /list-pets GET
`/api/history/history-log GET`

Test graphql in `http://localhost:8080/graphiql`
