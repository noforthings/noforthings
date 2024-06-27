package com.java_web.dto.reuqest;

import com.java_web.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class TodoListDTO {

    private Integer id;
    private String title;
    private String category;
    private String priority;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dueDate;
    private String createdTime;
    private String modifiedTime;
    private String subtask;
    private String reminder;
    private String notes;
    private String status;
//    private UserDTO userDTO;
}
