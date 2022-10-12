package de.rop.railopsplannerrestapi.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String initials;

    private String phone;

    private String fax;

}
