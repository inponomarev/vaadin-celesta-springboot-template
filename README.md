# Java + Spring Boot + Vaadin Flow + Celesta template

This is a template of Java + Spring Boot + Vaadin Flow + Celesta application

## Building the application

### Building the executable jar (development mode) 

* No tests: `mvnw verify -DskipTests`
* Unit tests only: `mvnw verify`
* Unit and UI tests: `mvnw verify -P it`


### Building the docker container

```
docker build -t myapp .
```

or, if you want to see the logs,

```
docker build --progress=plain myapp .
```


## Running and debugging the application

### Running the application from the command line.
To run from the command line, use `mvnw` and open http://localhost:8080 in your browser.

### Running and debugging the application in Intellij IDEA

- Locate the Application.java class in the Project view. It is in the src folder, under the main package's root.
- Right-click on the Application class
- Select "Debug 'Application.main()'" from the list

After the application has started, you can view it at http://localhost:8080/ in your browser. 

### Running the application from the docker container

```
docker run -d -p 8080:8080 myapp
```
