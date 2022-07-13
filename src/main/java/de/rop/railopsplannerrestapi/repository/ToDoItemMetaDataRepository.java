package de.rop.railopsplannerrestapi.repository;


import de.rop.railopsplannerrestapi.entity.ToDoItemMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemMetaDataRepository extends JpaRepository<ToDoItemMetaData, String> {
}
