package Utills;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ApiUtils {

    public static String endPointUrlFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\EndPoint.properties";

    public static String convertApiResponseString(Response response) {
        return response.asString();
    }

    public static JsonPath convertApiResponseJSONPath(Response response) {
        return new JsonPath(response.asString());
    }

    public static String rawTOJsonObject(JSONObject jsonObject) {//method name change String to json method
        return jsonObject.toString();
    }

}
