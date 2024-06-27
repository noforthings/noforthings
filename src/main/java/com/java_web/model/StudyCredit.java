package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tblstudycredit")
@Getter
@Setter
public class StudyCredit {
    @Id
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "stc_coding")
    private Integer coding;
    @Column(name = "stc_designing")
    private Integer designing;
    @Column(name = "stc_interact")
    private Integer interact;
    @Column(name = "stc_logical_thinking")
    private Integer logicalThinking;
    @Column(name = "stc_reliability")
    private Integer reliability;
    @Column(name = "stc_created_time")
    private String createdTime;
    @Column(name = "stc_modified_time")
    private String modifiedTime;
    @Column(name = "stc_notes")
    private String notes;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
