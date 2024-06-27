package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblsubject")
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private Integer id;
    @Column(name = "sub_name")
    private String name;
    @Column(name = "sub_dayinweek")
    private String dayInWeek;
    @Column(name = "sub_start_time")
    private String startTime;
    @Column(name = "sub_duration")
    private String duration;
    @Column(name = "sub_description")
    private String description;
    @Column(name = "sub_note")
    private String note;
    @Column(name = "sub_start_date")
    private String startDate;
    @Column(name = "sub_end_date")
    private String endDate;
    @Column(name = "sub_created_time")
    private String createdTime;
    @Column(name = "sub_modified_time")
    private String modifiedTime;
    @Column(name = "sub_coding")
    private Integer coding;
    @Column(name = "sub_logical_thinking")
    private Integer logicalThinking;
    @Column(name = "sub_designing")
    private Integer designing;
    @Column(name = "sub_interact")
    private Integer interact;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Score> scores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "smt_id")
    private Semester semesters;
    
    @Column(name = "course_id")
    private Integer courseId;
}
