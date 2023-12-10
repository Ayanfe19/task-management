## Task Management App

This repository is an implementation of a basic Task Management App. The current implementation serves all CRUD operations for Tasks.

### Getting Started

---

- Clone the repository.
- Create and populate the **.env** file using the [.env.example](https://github.com/Ayanfe19/task-management/blob/master/.env.example) as a guide.


### How to run

---

### As JAR using Maven

- Run ```mvn clean package``` from the project root to build jar.
- Run ```java -jar target/task-management-0.0.1-SNAPSHOT.jar``` to run.


### As Docker Container

- Run ```mvn clean package``` from the project root to build jar.
- Run ```docker build -t task-management-app .``` to build the image.
- Run ```docker run -p [host-port]:[app-port] task-management-app```



### Set up database

--- 
For test purposes, you can sping up the database as a docker container. 

```docker run -d -p 3306:3306 --name mysql-server -e MYSQL_ROOT_PASSWORD=<password> -e MYSQL_DATABASE=<task-db> -e MYSQL_USER=<your-user> -e MYSQL_PASSWORD=<password> mysql/mysql-server:latest```

### Dependencies

---

- Java 17+
- Spring Boot 3.2.0
- Lombok
- Dotenv 2.5.4
- ModelMapper 3.2.0

### Contributing

---

1. Clone this repository and create a named branch from the main branch to work in.
2. Make necessary changes and commit locally to your named development branch.
3. Carry out Peer Reviews on your changes to validate the quality.
4. Push your named branch to remote and raise a pull request to main branch.
5. I'll review the Pull Request and give proper feedback.



### License

---

This repository is not protected under any license. Feel free to use it for profit/non-profit causes.