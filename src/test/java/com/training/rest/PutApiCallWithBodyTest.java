package com.training.rest;

import utils.RestAssuredUtils;
import utils.APIRequestService;
import utils.FileReader;
import base.ResponseStatusCodes;

import static io.restassured.RestAssured.*;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.ResponseHelperForPutCallWithBody;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class PutApiCallWithBodyTest extends ResponseHelperForPutCallWithBody {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test
    public void hitThePutCallUsingBody() {
        Response response = APIRequestService.putCall(passJSonBodyForPutCall(), new FileReader().getDataFromPropertiesFile(RestAssuredUtils.endPointUrlFilePath).getProperty("putUrl"), passPathParaMeters());
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "morpheus", equalToIgnoringCase(validateResponse(response)));
    }
}
