package com.java_web.repository;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.model.TodoList;
import com.java_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    List<TodoList> findAll();
    boolean existsByTitle(String title);

}
