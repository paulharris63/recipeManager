# Recipe REST backend application
## Architectural choices
The application uses Spring boot to minimise boilerplate code for the REST controllers. For the same reason Spring Boot Data JPA/REST has been chosen for the persistence payer which removes boilerplate code for CRUD access to the database. The only bespoke code is the query filter. This has been designed so that the complete database is fetched and the filtering is done within the Recipe service. This would not scale well if the database were expected to become too large. If the database size were to become too large then the findAll method could be adapted to findBy a particular condition to reduce the database fetch size and then filtering could be applied to this smaller data set. The change would be confined to the Service class. 
## java version
The project is built against Java 11.

If upgrading to Java 17 we will need to look at the JaCoCo coverage configuration as this does not work currently.
## build
```
mvn clean install
```
## run
Currently running locally on windows with database location set to C:/data/database/ABNrecipes in the application.yaml file.
```
java -jar target/ABNinterviewRecipes-1.0-SNAPSHOT.jar
```
To run in another environment with a different database location set environment variable to point to database file
```
SPRING_DATASOURCE_URL=<database location>
```
If deploying with an existing database set the environment variables. 
```
SPRING_DATASOURCE_USERNAME=<username>
SPRING_DATASOURCE_PASSWORD=<password from secrets>
SPRING_DATASOURCE_DRIVER_CLASS_NAME=<driver class name>
```
## Code coverage
The Unit and integration test code coverage web summaries can be found in the target directory
```
target/site/jacoco-it/index.html
target/site/jacoco-ut/index.html
```
## API documentation : OpenAPI
When the application is running locally on port 9777:
- To get the OpenAPI JSON definition go to http://localhost:9777/v3/api-docs
- To get the OpenAPI YAML definition go to http://localhost:9777/v3/api-docs.yaml
- To get to the Swagger console go to http://localhost:9777/swagger-ui.html

Note that the YAML will download with most typical browser settings whereas the JSON will be displayed in the browser.
## Local test commands
Create some database entries:
```
curl -X POST http://localhost:9777/recipes -H "Content-Type: application/json" -d '{"id":null,"name":"Non-vegetarian recipe","servings":4,"vegetarian":false,"ingredients":"Meat, Potatoes","instructions":"Put in Oven"}'
curl -X POST http://localhost:9777/recipes -H "Content-Type: application/json" -d '{"id":null,"name":"Vegetarian recipe","servings":1,"vegetarian":true,"ingredients":"fruit and vegetables","instructions":"Peel and cook in pan"}'
```
Update some database entries:
(In the following commands the IDs will need to be updated if this is not a clean database.)
```
curl -X PUT http://localhost:9777/recipes/1 -H "Content-Type: application/json" -d '{"id":null,"name":"Non-vegetarian recipe","servings":6,"vegetarian":false,"ingredients":"Meat, Potatoes","instructions":"Put in Oven"}'
curl -X PUT http://localhost:9777/recipes/2 -H "Content-Type: application/json" -d '{"id":null,"name":"Vegetarian recipe","servings":2,"vegetarian":true,"ingredients":"fruit and vegetables","instructions":"Peel and cook in pan"}'
```
Perform the acceptance queries:
```
curl http://localhost:9777/recipes/query
curl 'http://localhost:9777/recipes/query?vegetarian=true'
curl 'http://localhost:9777/recipes/query?servings=4&include-ingredients=potatoes'
curl 'http://localhost:9777/recipes/query?exclude-ingredients=salmon&include-instructions=oven'

```
Remove the database entries: (In the following commands the IDs will need to be updated if this is not a clean database.)
```
curl -X DELETE http://localhost:9777/recipes/1
curl -X DELETE http://localhost:9777/recipes/2
```
## Still to do
Desireable items to do (outside scope but desirable):
- Create separate classes for API and DB Entities
- Allow multiple include and exclude terms
- Dockerise resulting app for deployment
- Prepare application for Azure PaaS deployment
- Secure with Spring Security against SSO service
- Configure CORS and XSRF
- Secure endpoints with roles
- If this were being built and deployed in a CI/CD pipeline I would remove most of the configuration from the application.yaml file and put it in environment variables.
- Impose unique constraint on database entry
- Refactor RecipeService to fetch smaller data sets.
- Rewrite acceptance test as JGiven test
- Refactor integration tests so that the database ID are not significant. Then multiple IT files can be created. Currently, the IDs are dependent upon what database actions have been performed in other ITs on the in memory database. The quick fix is to put all ITs ina single file.

