package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User extends Agent{

    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole role;


}
