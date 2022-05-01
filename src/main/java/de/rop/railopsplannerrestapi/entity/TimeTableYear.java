package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class TimeTableYear extends IdentifiedEntity{

    @Column(unique = true)
    String name;

    String firstDate;
    String lastDate;

    @OneToMany(mappedBy = "timeTableYear")
    List<RailNetwork> railNetworks;

}
