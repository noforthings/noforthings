package com.java_web.controller;

import com.java_web.dto.response.CareerOrientationResponse;
import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.model.Job;
import com.java_web.service.CareerOrientationService;
import com.java_web.service.SkillScoreService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/co")
public class CareerOrientationController {

    private final SkillScoreService skillScoreService;
    private final CareerOrientationService careerOrientationService;
    private final UserService userService;
    @GetMapping()
    public String CareerOrientationPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDTO user = userService.getByUsername(username);

        BigDecimal programmingSkill = skillScoreService.calculateProgrammingSkillPoint(user.getId());
        BigDecimal designSkill = skillScoreService.calculateDesignSkillPoint(user.getId());
        BigDecimal logicalThinking = skillScoreService.calculateLogicalThinkingPoint(user.getId());
        BigDecimal interactionSkill = skillScoreService.calculateInteractionSkillPoint(user.getId());

        Map<Integer, BigDecimal> scores = careerOrientationService.calculateTheAppropriateScore(user.getId());
        List<CareerOrientationResponse> jobs = careerOrientationService.getJobsById(scores);

        model.addAttribute("user", user);
        model.addAttribute("programmingSkill", programmingSkill);
        model.addAttribute("designSkill", designSkill);
        model.addAttribute("logicalThinking", logicalThinking);
        model.addAttribute("interactionSkill", interactionSkill);
        model.addAttribute("jobs", jobs);

        return "careerOrientationPage";
    }
}
