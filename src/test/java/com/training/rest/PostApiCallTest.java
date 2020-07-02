
package com.training.rest;

import Utills.ApiUtils;
import Utills.CallApiAndGetResponse;
import Utills.FileReader;
import base.ResponseStatusCodes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.RequestAndResponseHelperForPostCall;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class PostApiCallTest extends RequestAndResponseHelperForPostCall {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test(priority = 0)
    public void PostCallForCreatingUser() {
        Response response = CallApiAndGetResponse.postCall(passJsonBodyForCreatingUser(), new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("PostUrlForCreatingUser"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_201, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "morpheus", equalToIgnoringCase(validateResponse(response)));
    }

    @Test(priority = 1)
    public void postCallForLogin() {
        Response response = CallApiAndGetResponse.postCall(passJSonBodyForLogin(), new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("postUrlForLogin"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        Assert.assertTrue(validateResponseAfterLogin(response), "Failed at body");
    }

    @Test(priority = 2)
    public void postCallForUnSuccessFullLogin() {
        Response response = CallApiAndGetResponse.postCall(passJsonBodyForUnSuccessFullLogin(), new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("postUrlForLogin"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_400, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "Missing password", equalToIgnoringCase(validateResponseForUnSuccessFullLogin(response)));

    }
}

