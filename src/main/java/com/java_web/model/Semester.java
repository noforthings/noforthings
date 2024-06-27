package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tblsemester")
@Getter
@Setter
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smt_id")
    private Integer id;
    @Column(name = "smt_name")
    private String name;
    @Column(name = "smt_start_time")
    private String startTime;
    @Column(name = "smt_end_time")
    private String endTime;
    @Column(name = "smt_modified_time")
    private String modifiedTime;
    @Column(name = "smt_created_time")
    private String createdTime;
    @Column(name = "smt_notes")
    private String notes;
    @Column(name = "smt_goals")
    private String goals;

    @OneToMany(mappedBy = "semesters", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

}
