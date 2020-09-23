package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class APIRequestService {

    public static Response getCall(String url) {
        return given().contentType(ContentType.JSON).when().get(url);
    }

    public static Response getCall(String accessToken, String url) {
        return given().contentType(ContentType.JSON).auth().oauth2(accessToken).get(url);
    }

    public static Response getCall(HashMap<String, String> payLoadForQueryParam, String url) {
        return given().queryParams(payLoadForQueryParam).when().get(url);
    }

    public static Response getCall(String url, HashMap<String, String> payLoadForPathParam) {
        return given().contentType(ContentType.JSON).when().get(url, payLoadForPathParam);
    }

    public static Response putCall(String requestBody, String url, HashMap<String, String> pathParamInMap) {
        return given().contentType(ContentType.JSON).body(requestBody).when().put(url, pathParamInMap);
    }

    public static Response postCall(Object className, String url) {
        return given().contentType(ContentType.JSON).body(className).post(url);
    }

    public static Response postCall(String requestBody, String url) {
        return given().contentType(ContentType.JSON).body(requestBody).when().post(url);
    }

    public static Response postCall(String accessToken, String url, HashMap<String, String> pathParam) {
        return given().contentType(ContentType.JSON).auth().oauth2(accessToken).post(url, pathParam);
    }

    public static Response postCall(HashMap<String, String> formParam, String url) {
        return given().formParams(formParam).post(url);
    }
}
