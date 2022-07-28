package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Track extends IdentifiedEntity{

    private String name;
    private String trackNumber;
    private String lineNumber;
    private Boolean positiveDirection;

    @OneToMany(mappedBy = "track")
    @JsonIgnoreProperties({"track"})
    List<TrackStation> stations = new java.util.ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_group_ref")
    TrackGroup trackGroup;
}
