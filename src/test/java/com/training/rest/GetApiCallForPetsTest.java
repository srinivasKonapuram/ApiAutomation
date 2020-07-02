package com.training.rest;

import Utills.CallApiAndGetResponse;
import Utills.FileReader;
import Utills.ApiUtils;
import base.ResponseStatusCodes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requestResponse.ResponseHelperForPetsExample;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class GetApiCallForPetsTest extends ResponseHelperForPetsExample {
    @BeforeMethod
    public void loadEndUrl() {
        baseURI = new FileReader().getDataFromPropertiesFile(ApiUtils.endPointUrlFilePath).getProperty("baseUrlForPets");
    }

    @Test
    public void getApiMethodForPetsExampleWithPathParameter() {
       // Response response = given().contentType(ContentType.JSON).pathParam("id", 2).when().get("/api/unknown/{id}");
        Response response=CallApiAndGetResponse.getCall("/api/unknown/{id}",passPathParaMeters());
        Assert.assertEquals(ResponseStatusCodes.RESPONSE_STATUS_CODE_200, response.getStatusCode(), "Status Code Doest not matched");
        assertThat("Name does not matched", "fuchsia rose", equalToIgnoringCase(handleResponseOfPetsExample(response)));
    }


}
