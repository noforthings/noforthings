package com.java_web.service.impl;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.model.TodoList;
import com.java_web.model.User;
import com.java_web.repository.TodoListRepository;
import com.java_web.repository.UserRepository;
import com.java_web.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    //========== Lấy tất cả các todo thông qua USER ID ========
    @Override
    public List<TodoListDTO> getAllTodoList(Integer userId) {
        List<TodoList> todoLists = todoListRepository.findAll();
        List<TodoListDTO> todoListDoing = new ArrayList<>();
        for (TodoList todo : todoLists) {
            if (todo.getUser().getId() == userId) {
                todoListDoing.add(mapToDTO(todo));
            }
        }
        return todoListDoing;
    }

    //========= Lấy todo có status DOING =======
    @Override
    public List<TodoListDTO> getDoingTodoList() {
        List<TodoList> todoLists = todoListRepository.findAll();
        List<TodoListDTO> todoListDoing = new ArrayList<>();

        for (TodoList todo : todoLists) {
            if (todo.getStatus().equalsIgnoreCase("DOING")) {
                todoListDoing.add(mapToDTO(todo));
            };
        }
        return todoListDoing;
    }

    //========== Thêm Todo ==========
    @Override
    public boolean addTodoList(Integer userId, TodoListDTO todoListDTO) {
        User user = userRepository.findById(userId).get();

        List<TodoList> todoLists = user.getTodoLists();
        for (TodoList todo : todoLists) {
            if (todo.getTitle().equalsIgnoreCase(todoListDTO.getTitle())) {
                return false;
            }
        }

        TodoList todoList = mapToEntity(todoListDTO);
        todoList.setUser(user);
        todoListRepository.save(todoList);

        return true;
    }

    @Override
    public TodoListDTO updateTodoList(TodoListDTO todoListDTO) {
        return null;
    }

    // ===== Delete Todo =====
    @Override
    public boolean deleteTodoList(Integer todoId) {
        todoListRepository.deleteById(todoId);
        return true;
    }


    // ====================== Update ===================
    @Override
    public boolean updateTitle(Integer todoId, String title) {
        if (!todoListRepository.existsByTitle(title)) {
            TodoList todoList = todoListRepository.findById(todoId).get();
            todoList.setTitle(title);
            todoListRepository.save(todoList);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePriority(Integer todoId, String priority) {
        TodoList todoList = todoListRepository.findById(todoId).get();
        todoList.setPriority(priority);
        todoListRepository.save(todoList);
        return true;
    }

    @Override
    public boolean updateCategory(Integer todoId, String category) {
        TodoList todoList = todoListRepository.findById(todoId).get();
        todoList.setCategory(category);
        todoListRepository.save(todoList);
        return true;
    }

    @Override
    public boolean updateDueDate(Integer todoId, Date dueDate) {
        TodoList todoList = todoListRepository.findById(todoId).get();
        todoList.setDueDate(dueDate);
        todoListRepository.save(todoList);
        return true;
    }

    @Override
    public boolean updateStatus(Integer todoId, String status) {
        TodoList todoList = todoListRepository.findById(todoId).get();
        todoList.setStatus(status);
        todoListRepository.save(todoList);
        return true;
    }

    // update at Home Page from doing -> done
    @Override
    public boolean updateStatusAtHomePage(Integer todoId) {
        TodoList todoList = todoListRepository.findById(todoId).get();
        todoList.setStatus("DONE");
        todoListRepository.save(todoList);
        return true;
    }

    public static TodoListDTO mapToDTO(TodoList todoList) {
        TodoListDTO todoListDTO = new TodoListDTO();

        todoListDTO.setId(todoList.getId());
        todoListDTO.setTitle(todoList.getTitle());
        todoListDTO.setCategory(todoList.getCategory());
        todoListDTO.setPriority(todoList.getPriority());
        todoListDTO.setDescription(todoList.getDescription());
        todoListDTO.setDueDate(todoList.getDueDate());
        todoListDTO.setCreatedTime(todoList.getCreatedTime());
        todoListDTO.setModifiedTime(todoList.getModifiedTime());
        todoListDTO.setSubtask(todoList.getSubtask());
        todoListDTO.setReminder(todoList.getReminder());
        todoListDTO.setNotes(todoList.getNotes());
        todoListDTO.setStatus(todoList.getStatus());

        return todoListDTO;
    }
    public static TodoList mapToEntity(TodoListDTO todoListDTO) {
        TodoList todoList = new TodoList();

        todoList.setId(100);
        todoList.setTitle(todoListDTO.getTitle());
        todoList.setCategory(todoListDTO.getCategory());
        todoList.setPriority(todoListDTO.getPriority());
        todoList.setDescription(todoListDTO.getDescription());
        todoList.setDueDate(todoListDTO.getDueDate());
        todoList.setCreatedTime(todoListDTO.getCreatedTime());
        todoList.setModifiedTime(todoListDTO.getModifiedTime());
        todoList.setSubtask(todoListDTO.getSubtask());
        todoList.setReminder(todoListDTO.getReminder());
        todoList.setNotes(todoListDTO.getNotes());
        todoList.setStatus(todoListDTO.getStatus());

        return todoList;
    }
}
