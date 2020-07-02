package Utills;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class CallApiAndGetResponse {

    public static Response getCall(String url) {
        return given().get(url);
    }

    public static Response getCall(String accessToken, String url) {
        return given().auth().oauth2(accessToken).get(url);
    }

    public static Response getCall(HashMap<String, String> payLoadForQueryParam, String url) {
        return given().when().queryParams(payLoadForQueryParam).get(url);
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
        return given().contentType(ContentType.JSON).when().body(requestBody).post(url);
    }

    public static Response postCall(String accessToken, String url, HashMap<String, String> pathParam) {
        return given().auth().oauth2(accessToken).post(url, pathParam);
    }
}
