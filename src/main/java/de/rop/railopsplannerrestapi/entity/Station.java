package de.rop.railopsplannerrestapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Station extends IdentifiedEntity{

    @Column(unique=true)
    String name;

    @Column(unique=true)
    String ds100;

    @Enumerated(EnumType.STRING)
    StationType stationType;

    //Value for calculating lost kilometers
    Float positionValue;

}

