package de.rop.railopsplannerrestapi.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Station extends IdentifiedEntity{

    @Column(unique=true)
    String name;

    @Column(unique=true)
    String rl100;

    @Enumerated(EnumType.STRING)
    StationType stationType;
}

