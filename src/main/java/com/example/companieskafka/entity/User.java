package com.example.companieskafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    private String keycloak_id;

    private String userName;

    private String name;

    private Integer company_id;

    private Boolean isEnabled;

    public User() {

    }
}
