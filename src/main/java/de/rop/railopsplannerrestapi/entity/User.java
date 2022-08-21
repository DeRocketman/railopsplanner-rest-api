package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends IdentifiedEntity{

    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole role;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String initials;
    private String phone;
    private String fax;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "clerk_measure",
            joinColumns = @JoinColumn(name = "clerk_ref"),
            inverseJoinColumns = @JoinColumn(name = "measure_ref")
    )
    List<Measure> measures;

}
