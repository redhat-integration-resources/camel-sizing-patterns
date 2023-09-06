package org.apache.camel.example;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
// import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.camel.quarkus.test.CamelQuarkusTestSupport;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;



// import io.quarkus.test.common.QuarkusTestResource;
// import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


@QuarkusTest
// public class SoapTest extends CamelTestSupport {
public class SoapTest extends CamelQuarkusTestSupport {

    @Inject
    CamelContext context;

    @Override
    protected CamelContext createCamelContext() throws Exception {
        return this.context;
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("cxf:bean:s1")
                    .id("backend-soap-listener")
                    .setBody().simple("${body[0]}")
                    .to("mock:backend")
                    .log("Stub got request: ${body}")
                    .to("language:constant:resource:classpath:response.xml");
            }
        };
    }

    @Test
    public void subscriber() throws Exception {

        //set Mock expectations
        MockEndpoint mock = getMockEndpoint("mock:backend"); 
        mock.expectedMessageCount(1);

        //prepare JSON call and set expectations
        given()
            .body("{\"id\": \"123\"}")
            .header("Content-Type", "application/json")
        .when()
            .post("/camel/subscriber/details")
        .then()
            // .log().all()
            .statusCode(200)
            .body(
                "fullName",     is("Some One"),
                "addressLine1", is("1 Some Street"),
                "addressLine2", is("Somewhere SOME C0D3"),
                "addressLine3", is("UK")
            );

        //validate stub expecations
        assertMockEndpointsSatisfied();

        //obtain request sent to SOAP backend
        org.example.s1.SubscriberRequest stubMessage = 
                mock.getExchanges().get(0).getIn().getBody(org.example.s1.SubscriberRequest.class);

        //validate request
        assertNotNull(stubMessage, "something is wrong.");
        assertEquals("123", stubMessage.getId(), "oh oh");
    }    
}
