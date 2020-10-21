
## Cinerama
.
#### Heroku

the API is deployed in ```https://cinerama-app.herokuapp.com/```
.
#### Local Setup
.
##### 1. Run DataBase Dump file in MySQL client

the Dump file is located in ```./resources```, the script will create a new user for the database

##### 2. Build API 

Enter the API directory ```./cinerama```
run ```mvn clean install```

##### 3. Start the API 

run ```java -jar target/cinerama-1.0.0-SNAPSHOT.jar```

the API will run on http://localhost:8080/api
.
### Postman Collection
.
the Postman Collection file is located in ./resources,
there is a default user in the database 
**username:** jorgesidgo
**pwd:** overflow7020

#### Data for Saving Movies

1. Directors
    - 1 -> Ari Aster
    - 2 -> Francis Ford Coppola
    - 3 -> John Carpenter
    - 4 -> Riddley Scott
    - 5 -> Wes Anderson
2. Genres
    - 1 -> Horror
    - 2 -> Drama
    - 3 -> SciFi
    - 4 -> Suspense
3. Availability
    - 1 -> Available
    - 2 -> Out of Stock
4. Image Type
    - 1 -> cover
    - 2 -> background
    - 3 -> thumbnail

#### Comments

content of image must be send as Base64 including prefix

#### Purchase Type

* 1 -> Purchase
* 2 -> Rent
