package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tbltodolist")
@Getter
@Setter
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer id;
    @Column(name = "todo_title")
    private String title;
    @Column(name = "todo_category")
    private String category;
    @Column(name = "todo_priority")
    private String priority;
    @Column(name = "todo_description")
    private String description;

    @Column(name = "todo_duedate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dueDate;

    @Column(name = "todo_created_time")
    private String createdTime;
    @Column(name = "todo_modified_time")
    private String modifiedTime;
    @Column(name = "todo_subtask")
    private String subtask;
    @Column(name = "todo_reminder")
    private String reminder;
    @Column(name = "todo_notes")
    private String notes;
    @Column(name = "todo_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
