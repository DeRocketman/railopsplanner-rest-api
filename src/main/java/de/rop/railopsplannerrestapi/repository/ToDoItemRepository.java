package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

}
