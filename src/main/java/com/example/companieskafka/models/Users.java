package com.example.companieskafka.models;

import lombok.Value;

@Value
public class Users {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String role;

    private String keycloak_id;

    private String company_id;
}
