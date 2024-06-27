package com.java_web.service.impl;

import com.java_web.dto.reuqest.TodoListDTO;
import com.java_web.dto.reuqest.UserDTO;
import com.java_web.model.TodoList;
import com.java_web.model.User;
import com.java_web.repository.UserRepository;
import com.java_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setBirthday(userDTO.getBirthday());
        user.setGender(userDTO.getGender());
        user.setMobilePhone(userDTO.getMobilePhone());
        user.setHomePhone(userDTO.getHomePhone());
        user.setOfficePhone(userDTO.getOfficePhone());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setCreatedTime(userDTO.getCreatedTime());
        user.setParentId(userDTO.getParentId());
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO getById(Integer id) {
        User user = userRepository.findById(id).get();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setGender(user.getGender());
        userDTO.setMobilePhone(user.getMobilePhone());
        userDTO.setHomePhone(user.getHomePhone());
        userDTO.setOfficePhone(user.getOfficePhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        List<TodoList> todoLists = new ArrayList<>();
        todoLists = user.getTodoLists();
        List<TodoListDTO> todoListDTOS = new ArrayList<>();

        for (TodoList todo : todoLists) {
            todoListDTOS.add(TodoListServiceImpl.mapToDTO(todo));
        }
        userDTO.setTodoLists(todoListDTOS);
        return userDTO;
    }

    @Override
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setGender(user.getGender());
        userDTO.setMobilePhone(user.getMobilePhone());
        userDTO.setHomePhone(user.getHomePhone());
        userDTO.setOfficePhone(user.getOfficePhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    @Override
    public Integer countUser() {
        List<User> userList = userRepository.findAll();
        return userList.size();
    }

    @Override
    public Integer getReliabilityByUserId(Integer id) {
        return userRepository.findById(id).get().getStudyCredit().getReliability();
    }

}
