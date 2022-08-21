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

    @OneToOne(mappedBy = "track")
    @JsonIgnoreProperties({"track"})
    StartEndStation startPoint;

    @OneToOne(mappedBy = "track")
    @JsonIgnoreProperties({"track"})
    StartEndStation endPoint;

    @OneToMany(mappedBy = "track")
    @JsonIgnoreProperties({"track"})
    List<CrossStation> crossStations = new java.util.ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_group_ref")
    TrackGroup trackGroup;

    public void addCrossStation(CrossStation crossStation) {
        crossStations.add(crossStation);
        crossStation.setTrack(this);
    }

    public void setStartPoint(StartEndStation startPoint) {
        this.startPoint = startPoint;
        startPoint.setTrack(this);
    }

    public void setEndPoint(StartEndStation endPoint) {
        this.endPoint = endPoint;
        endPoint.setTrack(this);
    }
}
