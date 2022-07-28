package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter @RequiredArgsConstructor
public class TrackStation extends IdentifiedEntity{
    Integer currentNumber;
    String name;
    String rl100;
    String StationType;
    Float positionValue;
    Float transferTime;
    Boolean railReplacementStop;
    Float railReplacementDrivingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_ref")
    private Track track;

}