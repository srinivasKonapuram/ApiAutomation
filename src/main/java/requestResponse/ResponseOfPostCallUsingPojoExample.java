package requestResponse;

import Utills.ApiUtils;
import base.ResponseStatusCodes;
import dataModels.UserDetails;
import io.restassured.response.Response;

public class ResponseOfPostCallUsingPojoExample extends ResponseStatusCodes {

    public static UserDetails validateResponseOfPostUsingPOjo(Response response) {
        System.out.println("Total Response" + ApiUtils.convertApiResponseString(response));
        //Convert response object as POJO class
        UserDetails userDetails = response.as(UserDetails.class);
        System.out.println("Name" + userDetails.getName());
        System.out.println("Id of User" + userDetails.getId());
        return userDetails;

    }
}
