package de.rop.railopsplannerrestapi.repository;


import de.rop.railopsplannerrestapi.entity.ToDoItemMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemMetaDataRepository extends JpaRepository<ToDoItemMetaData, Long> {
}
