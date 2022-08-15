package de.rop.railopsplannerrestapi.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Station extends IdentifiedEntity implements Serializable {

    @Column(unique=true)
    String name;

    @Column(unique=true)
    String rl100;

    @Enumerated(EnumType.STRING)
    StationType stationType;
}

