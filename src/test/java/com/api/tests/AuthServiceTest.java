package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.AuthRequest;
import com.api.models.response.AuthResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthServiceTest {

    @Test
    public void createTokenTest(){

        AuthService authService = new AuthService();
        Response response = authService.createToken(new AuthRequest("admin","password123"));
        //System.out.println(response.asPrettyString());
        AuthResponse authResponse = response.as(AuthResponse.class);
        Assert.assertNotNull(authResponse);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
