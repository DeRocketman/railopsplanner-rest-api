package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Agent extends IdentifiedEntity{

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String initials;
    private String phone;
    private String fax;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Measure> measures;
}
