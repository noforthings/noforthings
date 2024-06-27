package com.java_web.controller.TodoList;

import com.java_web.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class UpdateTodoController {

    private final TodoListService todoListService;

    @PostMapping("/update-title")
    public String updateTile(@RequestParam("id") Integer todoId,
                             @RequestParam("label") String label,
                             @RequestParam("userId") Integer userId,
                             RedirectAttributes redirectAttributes) {
        if (todoListService.updateTitle(todoId, label)) {
            redirectAttributes.addFlashAttribute("updateTile", true);
        } else {
            redirectAttributes.addFlashAttribute("updateTile", false);
        }
        return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
    }

    @PostMapping("/update-priority")
    public String updatePriority(@RequestParam("id") Integer todoId,
                             @RequestParam("priority") String priority,
                             @RequestParam("userId") Integer userId,
                             RedirectAttributes redirectAttributes) {
        if (todoListService.updatePriority(todoId, priority)) {
            redirectAttributes.addFlashAttribute("updatePriority", true);
        } else {
            redirectAttributes.addFlashAttribute("updatePriority", false);
        }
        return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
    }

    @PostMapping("/update-category")
    public String updateCategory(@RequestParam("id") Integer todoId,
                                 @RequestParam("category") String category,
                                 @RequestParam("userId") Integer userId,
                                 RedirectAttributes redirectAttributes) {
        if (todoListService.updateCategory(todoId, category)) {
            redirectAttributes.addFlashAttribute("updateCategory", true);
        } else {
            redirectAttributes.addFlashAttribute("updateCategory", false);
        }
        return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
    }

    @PostMapping("/update-dueDate")
    public String updateCategory(@RequestParam("id") Integer todoId,
                                 @RequestParam("date") Date date,
                                 @RequestParam("userId") Integer userId,

                                 RedirectAttributes redirectAttributes) {
        if (todoListService.updateDueDate(todoId, date)) {
            redirectAttributes.addFlashAttribute("updateDueDate", true);
        } else {
            redirectAttributes.addFlashAttribute("updateDueDate", false);
        }
        return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam("id") Integer todoId,
                               @RequestParam("status") String status,
                               @RequestParam(value = "userId", required = false) Integer userId,
                               @RequestParam("source") String source,
                               RedirectAttributes redirectAttributes) {
        if (todoListService.updateStatus(todoId, status)) {
            redirectAttributes.addFlashAttribute("updateStatus", true);
        } else {
            redirectAttributes.addFlashAttribute("updateStatus", false);
        }

        if ("index".equals(source)) {
            return "redirect:/"; // Redirect về trang index
        } else {
            return "redirect:/page/todo-list?userId=" + userId; // Redirect về trang danh sách công việc với userId
        }
    }
}
