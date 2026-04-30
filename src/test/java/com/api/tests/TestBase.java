package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.AuthRequest;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    protected static String token = null;
    private static final Faker faker = new Faker();


    public static String getFirstName(){
        return faker.name().firstName();
    }

    public static String getLastName(){
        return faker.name().lastName();
    }

    public static int getPrice(){
        return faker.number().numberBetween(1,1000);
    }

    @BeforeSuite
    public void setup(){

            AuthService authService = new AuthService();
            Response authResponse = authService.createToken(new AuthRequest("admin","password123"));
            token = authResponse.jsonPath().getString("token");

            if(token == null){
                System.err.println("Error in generating token");
            }
    }
}
