## Dependency
This service requires a backend up and running. You can find it under the folder:
 - camelsb/stubs/end1

Follow the instructions to run the stub service.

</br>

## Running the service

Run it executing the command below:

```
mvn -Dspring-boot.run.profiles=dev -s configuration/settings.xml
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

## Deploying on Openshift

Ensure you create/switch-to the namespace where you want to deploy the stub.

> **Note:** instructions are based on Camel for Spring Boot 3.14.x GA version.

Run the following command to trigger the deployment:
```
mvn oc:deploy -Popenshift -s configuration/settings.xml
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