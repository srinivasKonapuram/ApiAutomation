package com.training.rest;

import utils.RestAssuredUtils;
import utils.APIRequestService;
import utils.FileReader;
import base.ResponseStatusCodes;
import dataModels.UserDetails;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.ResponseOfPostCallUsingPojoExample;

import static org.hamcrest.MatcherAssert.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class PostCallWithPojoExampleTest extends ResponseOfPostCallUsingPojoExample {
    UserDetails userDetails = new UserDetails();

    @BeforeMethod
    public void loadData() {
        userDetails.setName("morpheus");
        userDetails.setJob("leader");
        baseURI = new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test
    public void postCallWithPojo() {
        Response response = APIRequestService.postCall(userDetails, new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("PostUrlForCreatingUser"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_201, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "morpheus", equalToIgnoringCase(validateResponseOfPostUsingPOjo(response).getName()));
    }
}
