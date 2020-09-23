package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class RestAssuredUtils {

    public static String endPointUrlFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\EndPoint.properties";

    public static String convertApiResponseToString(Response response) {
        return response.asString();
    }

    public static JsonPath convertApiResponseToJSONPath(Response response) {
        return new JsonPath(response.asString());
    }

    public static String rawTOJsonObject(JSONObject jsonObject) {//method name change String to json method
        return jsonObject.toString();
    }

}
