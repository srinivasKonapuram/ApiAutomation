package com.training.rest;

import Utills.ApiUtils;
import Utills.CallApiAndGetResponse;
import Utills.FileReader;
import base.ResponseStatusCodes;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.ResponseHelperForPutCallWithBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class PutApiCallWithBodyTest extends ResponseHelperForPutCallWithBody {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test
    public void hitThePutCallUsingBody() {
        Response response = CallApiAndGetResponse.putCall(passJSonBodyForPutCall(), "/api/users/{id}", passPathParaMeters());
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "morpheus", equalToIgnoringCase(validateResponse(response)));
    }
}
