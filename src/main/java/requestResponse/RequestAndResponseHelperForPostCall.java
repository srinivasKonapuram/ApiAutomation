package requestResponse;

import utils.RestAssuredUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class RequestAndResponseHelperForPostCall {
    public static String passJsonBodyForCreatingUser() {
        JSONObject bodyForCreatingUser = new JSONObject();
        bodyForCreatingUser.put("name", "morpheus");
        bodyForCreatingUser.put("job", "leader");
        return RestAssuredUtils.rawTOJsonObject(bodyForCreatingUser);
    }

    public static String passJSonBodyForLogin() {
        JSONObject loginBody = new JSONObject();
        loginBody.put("email", "eve.holt@reqres.in");
        loginBody.put("password", "cityslicka");
        return RestAssuredUtils.rawTOJsonObject(loginBody);
    }

    public static String passJsonBodyForUnSuccessFullLogin() {
        JSONObject unSuccessFullLoginBody = new JSONObject();
        unSuccessFullLoginBody.put("email", "peter@klaven");
        return RestAssuredUtils.rawTOJsonObject(unSuccessFullLoginBody);
    }

    public static String validateResponse(Response response) {
        System.out.println("Total Response For pojo test" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Name of user" + jsonPath.getString("name"));
        System.out.println("Id of User" + jsonPath.getString("id"));
        return jsonPath.getString("name");
    }

    public static boolean validateResponseAfterLogin(Response response) {
        System.out.println("Total Response" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("token   " + jsonPath.getString("token"));
        return true;
    }

    public static String validateResponseForUnSuccessFullLogin(Response response) {
        System.out.println("Total Response" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Error" + jsonPath.getString("error"));
        return jsonPath.getString("error");
    }
}
