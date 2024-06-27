package com.java_web.dto.reuqest;

import com.java_web.model.TodoList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private String birthday;
    private String gender;
    private String mobilePhone;
    private String homePhone;
    private String officePhone;
    private String email;
    private String address;
    private String jobarea;
    private String job;
    private String position;
    private String applyYear;
    private Short permission;
    private String notes;
    private String roles;
    private Short logined;
    private String createdTime;
    private String modifiedTime;
    private String lastLogined;
    private Integer parentId;
    private Byte actions;
    private Byte deleted;

    private List<TodoListDTO> todoLists = new ArrayList<>();
}
