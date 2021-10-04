
## Dependency
This service requires a backend up and running. You can find it under the folder:
 - fuse/stubs/end1

Follow the instructions to run the stub service.

</br>

## Running the service

Run it executing the command below:

```
mvn
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

