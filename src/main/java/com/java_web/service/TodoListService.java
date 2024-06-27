package com.java_web.service;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.model.TodoList;

import java.sql.Date;
import java.util.List;

public interface TodoListService {
    public List<TodoListDTO> getAllTodoList(Integer userId);
    public List<TodoListDTO> getDoingTodoList();
    public boolean addTodoList(Integer userId, TodoListDTO todoListDTO);
    public TodoListDTO updateTodoList(TodoListDTO todoList);
    public boolean deleteTodoList(Integer todoId);
    public boolean updateTitle(Integer todoId, String title);
    public boolean updatePriority(Integer todoId, String priority);
    public boolean updateCategory(Integer todoId, String category);
    public boolean updateDueDate(Integer todoId, Date dueDate);
    public boolean updateStatus(Integer todoId, String status);

    // update at Home Page from doing -> done
    public boolean updateStatusAtHomePage(Integer todoId);

}
