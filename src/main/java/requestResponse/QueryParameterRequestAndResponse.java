package requestResponse;

import Utills.ApiUtils;
import base.ResponseStatusCodes;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;

public class QueryParameterRequestAndResponse {

    public static HashMap<String, String> passQueryParameter() {
        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("q", "London,UK");
        myMap.put("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8");
        return myMap;
    }

    public static String validateResponse(Response response) {
        System.out.println("Total Response" + ApiUtils.convertApiResponseString(response));
        JsonPath jsonPath = ApiUtils.convertApiResponseJSONPath(response);
        System.out.println("Country" + jsonPath.getMap("sys").get("country"));
        System.out.println("ID" + jsonPath.getString("id"));
        System.out.println("name" + jsonPath.getString("name"));
        System.out.println("cod" + jsonPath.getString("cod"));
        assertThat("London", equalToIgnoringCase(jsonPath.getString("name")));
        return jsonPath.getString("name");
    }
}
