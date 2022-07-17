package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Line extends IdentifiedEntity{
    @Column(unique = true)
    String name;

    @ManyToMany(mappedBy = "lines")
    @JsonIgnoreProperties({"lines"})
    List<Track> tracks;
}
