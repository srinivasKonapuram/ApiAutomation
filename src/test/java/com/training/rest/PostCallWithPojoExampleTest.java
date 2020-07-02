package com.training.rest;

import Utills.ApiUtils;
import Utills.CallApiAndGetResponse;
import Utills.FileReader;
import base.ResponseStatusCodes;
import dataModels.UserDetails;
import io.restassured.http.ContentType;
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
        baseURI = new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test
    public void postCallWithPojo() {
      Response response= CallApiAndGetResponse.postCall(userDetails,new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("PostUrlForCreatingUser"));
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_201, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "morpheus", equalToIgnoringCase(validateResponseOfPostUsingPOjo(response).getName()));
    }
}
