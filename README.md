# Cyverse calcultor API
Calculation API for Cyverse game.


## Code Editor
Just import java maven project to your favorite development IDE. You should have java enviroment variable $JAVA_HOME version 11 LTS.

```console
./mvnw spring-boot:run
```
or using your own maven local instance (if it is available in your computer PATH)

```console
mvn spring-boot:run
```

## Swagger API Docs
You can get the API documentation using the integrated swagger-ui implementation. By default port should be 9095 but you can change it in the properties file.

```console
http://localhost:9095/cyverse-calculator/swagger-ui.html
```