package requestResponse;

import Utills.ApiUtils;
import Utills.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

public class ResponseHelperForPutCallWithBody {
    public static String passJSonBodyForPutCall() {
        JSONObject putCallJsonBody = new JSONObject();
        putCallJsonBody.put("name", "morpheus");
        putCallJsonBody.put("job", "zion resident");
        return putCallJsonBody.toString();
    }
    public static HashMap<String, String> passPathParaMeters() {
        HashMap<String, String> myHash = new HashMap<>();
        myHash.put("id", new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("pathParamValue"));
        return myHash;
    }

    public static String validateResponse(Response response) {
            System.out.println("Total Response" + ApiUtils.convertApiResponseString(response));
            JsonPath jsonPath = ApiUtils.convertApiResponseJSONPath(response);
            System.out.println("Name of user" + jsonPath.getString("name"));
            System.out.println("job of User" + jsonPath.getString("job"));
            return jsonPath.getString("name");
        }
}
