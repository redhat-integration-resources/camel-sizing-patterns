package org.apache.camel.example;

import java.util.HashMap;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockTestResource implements QuarkusTestResourceLifecycleManager {

    private WireMockServer server1;
    private WireMockServer server2;

    @Override
    public Map<String, String> start() {
        // Setup & start the server
        server1 = new WireMockServer(wireMockConfig().dynamicPort());
        server1.start();

        // Setup & start the server
        server2 = new WireMockServer(wireMockConfig().dynamicPort());
        server2.start();

        // create mock endpoint
        server1.stubFor(
            post(urlEqualTo("/camel/subscriber/details"))
            .willReturn(
                aResponse()
                .withHeader("Content-Type", "application/xml")
                .withStatus(200)
                .withBodyFile("individual1.xml")
            )
        );

        // create mock endpoint
        server2.stubFor(
            post(urlEqualTo("/camel/subscriber/details"))
            .willReturn(
                aResponse()
                .withHeader("Content-Type", "application/xml")
                .withStatus(200)
                .withBodyFile("individual2.xml")
            )
        );

        // obtain value as Camel property expects
        String host1 = server1.baseUrl().substring(server1.baseUrl().lastIndexOf("http://") + 7);
        String host2 = server2.baseUrl().substring(server2.baseUrl().lastIndexOf("http://") + 7);

        // Ensure the camel component API client passes requests through the WireMock proxy
        Map<String, String> conf = new HashMap<>();
        conf.put("api.backend1.host", host1);
        conf.put("api.backend2.host", host2);
        return conf;
    }

    @Override
    public void stop() {
        if (server1 != null) {
            server1.stop();
        }
        if (server2 != null) {
            server2.stop();
        }
    }
}
