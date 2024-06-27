package com.java_web.controller.TodoList;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.service.TodoListService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoListHomePageController {

    private final TodoListService todoListService;
    private final UserService userService;

    @GetMapping("/page/todo-list")
    public String todoListPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getByUsername(username);
        Integer userId = user.getId();
        List<TodoListDTO> todoListDTOS = todoListService.getAllTodoList(user.getId());
        model.addAttribute("todoLists", todoListDTOS);
        model.addAttribute("newTodo", new TodoListDTO()); // Thêm đối tượng TodoListDTO mới để binding form
        model.addAttribute("userId", userId);
        return "todolist";
    }
}
