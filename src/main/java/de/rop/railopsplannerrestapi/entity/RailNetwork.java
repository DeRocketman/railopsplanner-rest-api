package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Getter
@Setter
public class RailNetwork extends IdentifiedEntity{
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "time_table_year_ref")
    TimeTableYear timeTableYear;

    String name;
    String abbreviation;

    @OneToMany(mappedBy = "railNetwork")
    List<Measure> measures;
}
