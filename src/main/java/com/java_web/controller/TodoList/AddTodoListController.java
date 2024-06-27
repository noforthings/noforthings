package com.java_web.controller.TodoList;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.service.TodoListService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AddTodoListController {

    private final TodoListService todoListService;
    private final UserService userService;

    @PostMapping("/add-todo")
    public String addTodo(@ModelAttribute("newTodo") TodoListDTO newTodo,
                          @RequestParam("userId") Integer userId,
                          RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getByUsername(username);
        if (todoListService.addTodoList(userId, newTodo)) {
            redirectAttributes.addFlashAttribute("success", true);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
        }
        return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
    }

}
