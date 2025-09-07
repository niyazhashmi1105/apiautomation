package com.api.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AuthResponse {

    public AuthResponse(){}
    private String token;
}
