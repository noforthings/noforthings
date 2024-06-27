package com.java_web.service;

import com.java_web.dto.reuqest.UserDTO;

public interface UserService {
    public UserDTO addUser(UserDTO userDTO);
    public UserDTO getById(Integer id);
    public UserDTO getByUsername(String username);
    public Integer countUser();
    public Integer getReliabilityByUserId(Integer id);
}
