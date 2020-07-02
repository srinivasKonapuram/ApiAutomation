package requestResponse;

import Utills.ApiUtils;
import Utills.CallApiAndGetResponse;
import base.ResponseStatusCodes;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;

public class ResponseHelperForWithoutHeaderCall extends ResponseStatusCodes {
    public static String validateResponseForGetCall(Response response) {
        //Your validations
        System.out.println("Total Response" + ApiUtils.convertApiResponseString(response));
        JsonPath jsonPath = ApiUtils.convertApiResponseJSONPath(response);
        JSONArray jsonArray = new JSONArray(jsonPath.getList("data"));
        //then do next filers are operations;
        System.out.println("Size" + jsonArray.length());
        for (int k = 0; k < jsonArray.length(); k++) {
            System.out.println("Emp Details at Index" + k + "is " + jsonArray.get(k).toString());
        }
        return jsonArray.getJSONObject(2).getString("employee_name");
    }

}
