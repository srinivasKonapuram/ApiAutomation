
package com.training.rest;

import utils.RestAssuredUtils;
import utils.APIRequestService;
import utils.FileReader;
import base.ResponseStatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.RequestAndResponseHelperForOAuthCalls;

import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class OAuthDemoTest extends RequestAndResponseHelperForOAuthCalls {
    String accessToken;

    @BeforeClass
    public void GenerateAccessToken() {
        Response response = APIRequestService.postCall(passFormParaMeters(), new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("GenerateAccessToken"));
        accessToken = response.getBody().jsonPath().getString("access_token");
        Assert.assertEquals(200, response.getStatusCode(), "AccessToken not generated");
    }

    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("OAuthURl");
    }

    @Test(priority = 0)
    public void getCallForRetrievesUserThatTiedToTheAccessToken() {
        Response response = APIRequestService.getCall(accessToken, new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("profile"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Email did not matched", "konapuramsrinivas677@gmail.com", equalToIgnoringCase(validateResponseOfMe(response)));
    }

    @Test(priority = 1)
    public void PostCallForFeedYourChicken() {
        Response response = APIRequestService.postCall(accessToken, new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("feedYourChicken"), pathParam());
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "chickens-feed", equalToIgnoringCase(validateResponseOfFeedYourChicken(response)));
    }

    @Test(priority = 2)
    public void postCallForEggsCollectedTodayOutOfScope() {
        Response response = APIRequestService.postCall(accessToken, new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("outOfScope"), pathParam());
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_401, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "insufficient_scope", equalToIgnoringCase(validateEggsCollectedToday(response)));
    }

}

