package com.api.base;

import com.api.models.request.AuthRequest;
import io.restassured.response.Response;

public class AuthService extends BaseService{

    private static final String BASE_PATH = "/auth";

    public Response createToken(AuthRequest payload){
        return postRequest(payload,BASE_PATH);

    }
}
