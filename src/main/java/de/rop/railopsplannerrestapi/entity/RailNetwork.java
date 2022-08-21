package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class RailNetwork extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "time_table_year_ref")
    TimeTableYear timeTableYear;

    String name;
    String abbreviation;

    @OneToMany(mappedBy = "railNetwork")
    @JsonIgnoreProperties({"railNetwork"})
    List<Measure> measures;

    @OneToMany(mappedBy = "railNetwork")
    @JsonIgnoreProperties({"railNetwork"})
    List<TrackGroup> trackGroups;
}
