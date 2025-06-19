package com.example.springbootpostgresqlcrud.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Registerrequest {
    private String username;
    private String email;
    private String password;
}
