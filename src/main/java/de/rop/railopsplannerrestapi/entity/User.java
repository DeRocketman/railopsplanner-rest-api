package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends Agent{

    private String role;

    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
}
