package com.pustot.studling.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String cognitoSub;
    private String username;
}