package com.api.base.service;

import io.restassured.response.Response;

public class AuthService extends BaseService {

    private static final String BASE_PATH = "/auth";

    public Response createToken(Object payload){
        return postRequest(payload,BASE_PATH);

    }
}
