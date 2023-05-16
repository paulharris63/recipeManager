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
java -jar target/
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

## OpenAPI
When the application is running locally on port 9777:
- To get the OpenAPI JSON definition go to http://localhost:9777/v3/api-docs
- To get the OpenAPI YAML definition go to http://localhost:9777/v3/api-docs.yaml

Note that the YAML will download with most typical browser settings whereas the JSON will be displayed in the browser.

## Still to do
Necessary items to do:
- Add query with filters
- Get Swagger UI working (from springdoc-openapi : throws java.lang.StringIndexOutOfBoundsException when accessing http://localhost:9777/swagger-ui)

Desireable items to do (outside scope but desirable):
- Dockerise resulting app for deployment
- Prepare application for Azure PaaS deployment
- Secure with Spring Security against SSO service
- Configure CORS and XSRF
- Secure endpoints with roles
- If this were being built and deployed in a CI/CD pipeline I would remove most of the configuration from the application.yaml file and put is in environment variables.

Local test commands
```
curl http://localhost:9777/recipes/query
curl -X POST http://localhost:9777/recipes -H "Content-Type: application/json" -d '{"id":null,"name":"Recipe name","numberOfServings":4,"vegetarian":false,"ingredients":"ingredients","instructions":"instructions"}'
```
