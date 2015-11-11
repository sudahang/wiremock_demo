import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Rule;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by suh on 11/11/15.
 */
public class WiremockDemoTests {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule();
//    protected static final Logger logger = LoggerFactory.getLogger(WiremockDemoTests.class);
    @Test
    public void exampleTest() {
//        WireMockServer wireMockServer = new WireMockServer(); //No-args constructor will start on port 8080, no HTTPS
//        wireMockServer.start();

        wireMockRule.stubFor(get(urlEqualTo("/mycar/Chevy"))
                .willReturn(aResponse()
                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
                        .withBody("<response>Chevy car response body</response>")));
        String url = "http://localhost:8080/mycar/Chevy";
        String method = "GET";
        String body = "Chevy car response body";

        Response response = given().get(url);
        assertEquals(200, response.getStatusCode());
        assertEquals("Chevy car response body", response.jsonPath().get("message"));
    }
}
