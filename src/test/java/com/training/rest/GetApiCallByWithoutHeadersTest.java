package com.training.rest;

import utils.RestAssuredUtils;
import utils.APIRequestService;
import utils.FileReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.ResponseHelperForWithoutHeaderCall;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class GetApiCallByWithoutHeadersTest extends ResponseHelperForWithoutHeaderCall {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("baseUrl");
    }

    @Test
    public void getMethodWithoutHeader() {
        Response response = APIRequestService.getCall(new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("getUrl"));
        Assert.assertEquals(RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "Ashton Cox", equalToIgnoringCase(validateResponseForGetCall(response)));
    }
}
