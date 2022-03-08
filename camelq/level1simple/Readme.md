## Running JUnits

The service includes a JUnit to showcase how unit testing can be implemented for Camel Quarkus implementations.

Run the JUnits with the command below:

```
mvn clean test
```

## Running the service

>**Note**: the stub needs to be up and running for a successful end-to-end execution. Refer to the stub's Readme doc for instructions to run it.

Run it locally executing the command below:

```
mvn clean compile quarkus:dev
```

## Test with cURL

You can send a `POST` request with the following `curl` command:

```
curl \
-H "content-type: application/json" \
-d '{"id":"123"}' \
http://localhost:8080/camel/subscriber/details
```

## Discover and test with the Swagger UI

You can discover the *OpenApi* service specification with the following `curl` command:

```
curl http://localhost:8080/camel/openapi.json
```

You can also open the Swagger UI by opening the URL in a browser

 - http://localhost:8080/camel/openapi.json

Click on the service to try, and hit the `Try it out` button, scroll down and then click `Execute`

<br>

## Deploying to Openshift

Ensure you create/switch-to the namespace where you want to deploy the stub.

Run the following command to trigger the deployment:
```
mvn clean package -DskipTests -Dquarkus.kubernetes.deploy=true
```

To test the stub once deployed, open a tunnel with the following command:
```
oc port-forward service/simple 8080
```

You can discover the *OpenApi* service specification with the following `curl` command:

```
curl http://localhost:8080/camel/openapi.json
```

You can send a `POST` request with the following `curl` command:

```
curl \
-H "content-type: application/json" \
-d '{"id":"123"}' \
http://localhost:8080/camel/subscriber/details
```


