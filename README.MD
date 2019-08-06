1. From main dir run mvn spring-boot:run
2. After app is started listed endpoints should be available:

GET      http://localhost:8080/employee - list all employees

GET      http://localhost:8080/employee/{id} - retrieves employee with specified id (if exists)

POST     http://localhost:8080/employee - creates new employee

PUT      http://localhost:8080/employee/{id} - updates employee with specified id (if exists)

DELETE   http://localhost:8080/employee/{id} - deletes employee with specified id (if exists)

GET      http://localhost:8080/employee/find?name=xx&surname=??&address=??&job=?? - searches for a customer
         with specified parameters, all parameters all optional

Please notice that for POST and PUT methods Content-type: application/json header must be sent in request,
request body should be json eg. {"name": "Krzysztof", "surname": "Tkacz", "grade": "10", "salary": "9999"}

