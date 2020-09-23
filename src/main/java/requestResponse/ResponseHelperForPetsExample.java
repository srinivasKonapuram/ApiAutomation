package requestResponse;

import utils.RestAssuredUtils;
import utils.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
;import java.util.HashMap;

public class ResponseHelperForPetsExample {
    public static String handleResponseOfPetsExample(Response response) {
        System.out.println("Total Response" + RestAssuredUtils.convertApiResponseToString(response));
        JsonPath jsonPath = RestAssuredUtils.convertApiResponseToJSONPath(response);
        System.out.println("Color is " + jsonPath.getMap("data").get("color"));
        return jsonPath.getMap("data").get("name").toString();
    }

    public static HashMap<String, String> passPathParaMeters() {
        HashMap<String, String> myHash = new HashMap<>();
        myHash.put("id", new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("pathParamValue"));
        return myHash;
    }
}
