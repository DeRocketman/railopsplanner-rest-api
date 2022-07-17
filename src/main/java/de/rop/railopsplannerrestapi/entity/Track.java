package de.rop.railopsplannerrestapi.entity;

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

    @ManyToMany(targetEntity = Line.class)
    @JoinColumn(name = "line_ref")
    List<Line> lines;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "start_point")
    Station startPoint;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "end_point")
    Station endPoint;

    @ManyToMany(targetEntity = Station.class)
    @JoinColumn(name = "cross_station")
    List<Station> crossStations;
}
