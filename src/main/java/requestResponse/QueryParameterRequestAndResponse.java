package requestResponse;

import utils.RestAssuredUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

public class QueryParameterRequestAndResponse {

    public static HashMap<String, String> passQueryParameter() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("q", "London,UK");
        myMap.put("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8");
        return myMap;
    }

    public static String validateResponse(Response response) {
        System.out.println("Total Response" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Country" + jsonPath.getMap("sys").get("country"));
        System.out.println("ID" + jsonPath.getString("id"));
        System.out.println("name" + jsonPath.getString("name"));
        System.out.println("cod" + jsonPath.getString("cod"));
        return jsonPath.getString("name");
    }
}
