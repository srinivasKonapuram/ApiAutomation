package com.training.rest;

import Utills.ApiUtils;
import Utills.CallApiAndGetResponse;
import Utills.FileReader;
import base.ResponseStatusCodes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.QueryParameterRequestAndResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static io.restassured.RestAssured.baseURI;

public class QueryParameterCallTest extends QueryParameterRequestAndResponse {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("weatherUrl");
    }

    @Test
    public void hitApiForQueryParameter() {
        Response response=  CallApiAndGetResponse.getCall(passQueryParameter(),"/weather");
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "London", equalToIgnoringCase(validateResponse(response)));
    }
}
