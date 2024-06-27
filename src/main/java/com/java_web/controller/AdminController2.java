package com.java_web.controller;

import com.java_web.dto.reuqest.StatDTO;
import com.java_web.service.JobService;
import com.java_web.service.TodoListService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController2 {

    private final UserService userService;
    private final TodoListService todoListService;
    private final JobService jobService;
    @GetMapping("/admin/nam")
    public String adminPage(Model model) {
        Integer numberOfStudents = userService.countUser();
        Integer numberOfTodoDoing = todoListService.getDoingTodoList().size();

        List<StatDTO> list = jobService.getAvgScore();
        List<Integer> userId = new ArrayList<>();
        List<String> user = new ArrayList<>();
        List<String> userScore = new ArrayList<>();
        List<Integer> total = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.##");
        list.forEach(item -> {
            user.add(item.getId() + " " + item.getName());
            userScore.add(df.format(item.getScore()));
            total.add(item.getTotal());
        });

        model.addAttribute("user", user);
        model.addAttribute("score", userScore);
        model.addAttribute("total", total);
        model.addAttribute("numberOfStudents", numberOfStudents);
        model.addAttribute("numberOfTodoDoing", numberOfTodoDoing);
        return "admin";
    }
}
