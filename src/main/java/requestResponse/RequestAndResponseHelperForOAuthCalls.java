package requestResponse;

import utils.RestAssuredUtils;
import utils.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

public class RequestAndResponseHelperForOAuthCalls {
    public static HashMap<String, String> passFormParaMeters() {
        HashMap<String, String> myHash = new HashMap<>();
        myHash.put("client_id", "DemoAPPForOAuth");
        myHash.put("client_secret", "f6c16f08e467d7bde988beab2649a402");
        myHash.put("grant_type", "client_credentials");
        return myHash;
    }

    public static HashMap<String, String> pathParam() {
        HashMap<String, String> myHash = new HashMap<>();
        myHash.put("id", new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("OAuthID"));
        return myHash;
    }

    public static String validateResponseOfMe(Response response) {
        System.out.println("Total Response as String" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Total Response as JSON" + jsonPath.prettify());
        System.out.println("email" + jsonPath.get("email"));
        System.out.println("ID" + jsonPath.getString("id"));
        System.out.println("FirstName" + jsonPath.getString("firstName"));
        System.out.println("LastName" + jsonPath.getString("lastName"));
        return jsonPath.getString("email");
    }

    public static String validateResponseOfFeedYourChicken(Response response) {
        System.out.println("Total Response as String" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Total Response as JSON" + jsonPath.prettify());
        return jsonPath.getString("action");

    }

    public static String validateEggsCollectedToday(Response response) {
        System.out.println("Total Response as String" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Total Response as JSON" + jsonPath.prettify());
        return jsonPath.getString("error");
    }
}