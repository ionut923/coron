package com.steps.https;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.tools.constants.Constants;

import net.thucydides.core.steps.ScenarioSteps;

import java.util.HashMap;
import java.util.Map;

public class AbstractHttpsSteps extends ScenarioSteps {
    private static final long serialVersionUID = 1L;
    public static RequestSpecification specs = null;
    public static Map<String, String> extraHeaders = new HashMap<String, String>();

    public static RequestSpecification getSpecWithExtraHeaders() {
        specs = new RequestSpecBuilder()
                .setBaseUri(Constants.BASE_URL)
                .addHeaders(extraHeaders)
                .build();
        return specs;
    }

    protected Response getRequest(String path) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .when()
                .get(path.replace(" ", "-"))
                .then()
                .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
                .extract().response();
    }

    protected static Response postRequest(String path, Map<String, String> params) {
        return given().relaxedHTTPSValidation()
                .spec(getSpecWithExtraHeaders())
                .parameters(params)
                .when()
                .post(path.replace(" ", "-"))
                .then()
                .assertThat().statusCode(anyOf(is(201), is(200), is(302)))
                .extract().response();
    }
}
