package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ToDoItem extends IdentifiedEntity {

    @ManyToOne
    @JoinColumn(name = "measure_ref")
    Measure measure;

    String name;
    String deadline;
    String closingDate;
    String testMark;

    @OneToOne(mappedBy = "toDoItem")
    ToDoItemMetaData metaData;

}
