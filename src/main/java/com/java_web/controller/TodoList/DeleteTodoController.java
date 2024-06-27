package com.java_web.controller.TodoList;

import com.java_web.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class DeleteTodoController {

    private final TodoListService todoListService;

    @PostMapping("/delete-todo")
    public String deleteTodo(@RequestParam("id") Integer todoId,
                             @RequestParam("userId") Integer userId,
                             RedirectAttributes redirectAttributes) {
        boolean isDeleted = todoListService.deleteTodoList(todoId);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("deleteSuccess", true);
        } else {
            redirectAttributes.addFlashAttribute("deleteSuccess", false);
        }
        return "redirect:/page/todo-list?userId=" + userId;
    }

}
