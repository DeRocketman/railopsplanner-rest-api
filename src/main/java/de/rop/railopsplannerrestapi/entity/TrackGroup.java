package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "track_group")
@Getter @Setter @RequiredArgsConstructor
public class TrackGroup extends IdentifiedEntity{
   String name;

   @OneToMany(mappedBy = "trackGroup")
   List<Track> tracks;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "rail_network_ref")
   RailNetwork railNetwork;


}