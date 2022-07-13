package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class IdentifiedEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name="id", unique = true)
    String id;
    
}
