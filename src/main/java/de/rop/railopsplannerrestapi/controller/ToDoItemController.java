package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.ToDoItem;
import de.rop.railopsplannerrestapi.repository.ToDoItemRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/to-do-item")
public class ToDoItemController {
    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping("")
    public List<ToDoItem> index() {
        return toDoItemRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoItem newToDoItem(@RequestBody ToDoItem newToDoItem) {
        return toDoItemRepository.save(newToDoItem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable("id") String id, @RequestBody ToDoItem toDoItem) {
        Optional<ToDoItem> storedToDoItem = toDoItemRepository.findById(id);

        if (storedToDoItem.isPresent()) {
            return new ResponseEntity<>(toDoItemRepository.save(toDoItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> show(@PathVariable("id") String id) {
        Optional<ToDoItem> selectedToDoList = this.toDoItemRepository.findById(id);
        if (selectedToDoList.isPresent()) {
            return new ResponseEntity<>(selectedToDoList.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.toDoItemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
