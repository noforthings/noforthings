package com.java_web.controller;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.model.*;
import com.java_web.repository.StudyCreditRepository;
import com.java_web.repository.TodoListRepository;
import com.java_web.repository.UserRepository;
import com.java_web.service.CareerOrientationService;
import com.java_web.service.SkillScoreService;
import com.java_web.service.TodoListService;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {

    private final StudyCreditRepository creditRepository;
    private final TodoListService todoListService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TodoListRepository todoListRepository;
    private final SkillScoreService skillScoreService;

    @GetMapping("/credit/all")
    public List<StudyCredit> getAllCredit() {
        return creditRepository.findAll();
    }

    @GetMapping("/user")
    public UserDTO getById(@RequestParam("userId") Integer userId) {
        return userService.getById(userId);
    }
    @GetMapping("/users")
    public UserDTO getByIdx() {
        return userService.getById(1);
    }

    @GetMapping("/todo")
    public List<TodoListDTO> getAll(@RequestParam("userId") Integer userId) {
        return todoListService.getAllTodoList(userId);
    }

    @PostMapping("/delete-todo")
    public boolean delete(@RequestParam("todoId") Integer todoId) {
        return todoListService.deleteTodoList(todoId);

    }

    @PostMapping("/todo/add")
    public ResponseEntity<?> addTodoListx(@RequestBody TodoListDTO todoList,
                                          @RequestParam("userId") Integer userId) {
        todoListService.addTodoList(userId, todoList);
        return ResponseEntity.ok().body("Add to-do list for user successful");
    }

    @GetMapping("/score/getAll")
    public List<BigDecimal> getAll() {
        List<BigDecimal> decimalList = new ArrayList<>();
        decimalList.add(skillScoreService.calculateProgrammingSkillPoint(1));
        decimalList.add(skillScoreService.calculateDesignSkillPoint(1));
        decimalList.add(skillScoreService.calculateInteractionSkillPoint(1));
        decimalList.add(skillScoreService.calculateLogicalThinkingPoint(1));
        return decimalList;
    }

    private final CareerOrientationService careerOrientationService;

//    @GetMapping("/score/x")
//    public List<Job> getScore() {
//
//        Map<Integer, BigDecimal> ca = careerOrientationService.calculateTheAppropriateScore(1);
//        return careerOrientationService.getJobsById(ca);
//    }

}
