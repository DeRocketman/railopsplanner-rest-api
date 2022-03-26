package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String phone;
    private String fax;
    private String role;


    @JsonIgnore
    private String password;

}
