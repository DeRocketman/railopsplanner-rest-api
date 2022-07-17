package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class TrackGroup extends IdentifiedEntity {
    String name;

    @ManyToOne
    @JoinColumn(name = "rail_network_ref")
    RailNetwork railNetwork;

    @OneToMany
    @JoinColumn(name = "track_ref")
    List<Track> tracks;
}
