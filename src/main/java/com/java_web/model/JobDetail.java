package com.java_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbljobdetail")
@Getter
@Setter
public class JobDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Integer id;

    @Column(name = "job_coding")
    private int coding;

    @Column(name = "job_designing")
    private int designing;
    @Column(name = "job_logical_thinking")
    private int logicalThinking;

    @Column(name = "job_interact")
    private int interact;

    @Column(name = "job_reliability")
    private int reliability;

    @Column(name = "job_detail_created_time")
    private String detailCreatedTime;

    @Column(name = "job_detail_modified_time")
    private String detailModifiedTime;
}
