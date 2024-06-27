package com.java_web.controller;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.service.SkillScoreService;
import com.java_web.service.TodoListService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomePageController {

    private final SkillScoreService skillScoreService;
    private final TodoListService todoListService;
    private final UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getByUsername(username);
        BigDecimal programmingSkill = skillScoreService.calculateProgrammingSkillPoint(user.getId());
        BigDecimal designSkill = skillScoreService.calculateDesignSkillPoint(user.getId());
        BigDecimal logicalThinking = skillScoreService.calculateLogicalThinkingPoint(user.getId());
        BigDecimal interactionSkill = skillScoreService.calculateInteractionSkillPoint(user.getId());
        Integer reliability = userService.getReliabilityByUserId(user.getId());
        List<TodoListDTO> todoListDTOS = todoListService.getAllTodoList(user.getId());

        model.addAttribute("programmingSkill", programmingSkill);
        model.addAttribute("designSkill", designSkill);
        model.addAttribute("logicalThinking", logicalThinking);
        model.addAttribute("interactionSkill", interactionSkill);
        model.addAttribute("reliability", reliability);
        model.addAttribute("todos", todoListDTOS);
        return "index";
    }
}

