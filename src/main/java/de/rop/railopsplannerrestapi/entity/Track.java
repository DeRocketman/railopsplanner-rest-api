package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Track extends IdentifiedEntity {

    String name;

    boolean positiveDirection = true;

    String trackNumber;

    String lineNumber;

    @OneToMany(mappedBy = "track")
    @JsonIgnoreProperties({"track"})
    List<TrackStation> stations;

    @ManyToOne
    @JoinColumn(name = "track_group_ref")
    TrackGroup trackGroup;
}
