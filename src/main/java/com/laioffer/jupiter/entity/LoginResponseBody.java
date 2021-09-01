package com.laioffer.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Description:
 * @Author RileyShen
 * @Create 2021-09-01
 */
public class LoginResponseBody {
    @JsonProperty("userId")
    private final String userId;


    @JsonProperty("name")
    private final String name;

    public LoginResponseBody(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
