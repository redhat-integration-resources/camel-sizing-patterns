/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@QuarkusTestResource(WireMockTestResource.class)
public class ServiceTest {

    @Test
    public void subscriber() {

        given()
        	.body("{\"id\": \"123\"}")
        	.header("Content-Type", "application/json")
        .when()
            .post("/camel/subscriber/details")
        .then()
            .statusCode(200)
            .body(
                "client.fullName",     is("Some One"),
                "client.addressLine1", is("1 Some Street"),
                "client.addressLine2", is("Somewhere UK"),
                "client.addressLine3", is("SOME C0D3"),

                "subscriptions.period.start",   is("01-01-2022"),
                "subscriptions.period.end",     is("01-01-2023"),

                "subscriptions.packages[0].id",     is("i-001"),
                "subscriptions.packages[0].amount", is("16.00"),

                "subscriptions.packages[1].id",     is("i-002"),
                "subscriptions.packages[1].amount", is("32.00"),

                "subscriptions.packages[2].id",     is("i-018"),
                "subscriptions.packages[2].amount", is("200.00")
            );
    }
}
