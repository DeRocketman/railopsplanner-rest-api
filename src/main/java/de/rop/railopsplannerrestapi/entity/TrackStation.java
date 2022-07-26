package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TrackStation extends IdentifiedEntity{

    Integer currentNumber;

    String name;

    String rl100;

    @Enumerated(EnumType.STRING)
    StationType stationType;

    //Value for calculating lost kilometers
    Float positionValue;

    Integer transferTime;

    Boolean railReplacementStop = true;

    Float railReplacementDrivingTime;

    @ManyToOne
    @JoinColumn(name = "track_ref")
    Track track;
}
