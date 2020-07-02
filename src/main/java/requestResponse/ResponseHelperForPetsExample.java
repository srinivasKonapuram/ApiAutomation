package requestResponse;

import Utills.ApiUtils;
import Utills.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
;import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ResponseHelperForPetsExample {
    public static String handleResponseOfPetsExample(Response response) {
        System.out.println("Total Response" + ApiUtils.convertApiResponseString(response));
        JsonPath jsonPath = ApiUtils.convertApiResponseJSONPath(response);
        System.out.println("Color is " + jsonPath.getMap("data").get("color"));
        return jsonPath.getMap("data").get("name").toString();
    }
    public static HashMap<String, String> passPathParaMeters() {
        HashMap<String, String> myHash = new HashMap<>();
        myHash.put("id", new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("pathParamValue"));
        return myHash;
    }
}
