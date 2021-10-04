
## Running the stub service

Run it executing the command below:

```
mvn
```

You can discover the *OpenApi* service specification with the following `curl` command:

```
curl http://localhost:9000/camel/api-docs
```

You can send a `POST` request with the following `curl` command:

>**Note**: it's a dummy stub and the payload to send can be empty

```
curl \
-H "content-type: application/xml" \
-d '' \
http://localhost:9000/camel/subscriber/details
```

