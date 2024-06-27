package com.java_web.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tblscore")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="score_id")
	private Integer id;
	@Column(name="score_times")
	private Integer times;
	@Column(name="score_type")
	private String type;
	@Column(name="score_value")
	private BigDecimal score;
	@Column(name="score_note")
	private String note;
	@Column(name="score_created_time")
	private String createdTime;
	@Column(name="score_modified_time")
	private String modifiedTime;
	@Column(name="score_deleted")
	private Boolean deleted;
	@Column(name="score_status")
	private String status;
	@Column(name="course_name")
	private String courseName;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "sub_id")
    private Subject subject;
}
