## java version
The project is built against Java 11.

If upgrading to Java 17 we will need to look at the JaCoCo coverage configuration.

## build
```
mvn clean install
```

## run
```
java -jar target/
```

## OpenAPI
To get the OpenAPI JSON definition go to http://localhost:9777/v3/api-docs

To get the OpenAPI YAML definition go to http://localhost:9777/v3/api-docs.yaml

Note that the YAML will download with most typical browser settings
