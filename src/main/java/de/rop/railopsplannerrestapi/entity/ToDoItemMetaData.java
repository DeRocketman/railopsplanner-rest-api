package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class ToDoItemMetaData extends IdentifiedEntity{
    @OneToOne(cascade = CascadeType.ALL)
    ToDoItem toDoItem;

    boolean isMilestoneItem;
    boolean isTestMarkNeeded;
    boolean isRelativeToStart;
    boolean isGlobal;
}
