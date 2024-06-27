package com.java_web.dto.reuqest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobDTO {
    private Integer id;

    private String name;

    private String nameEnglish;

    private String require;

    private String requireEnglish;

    private String links;

    private String skills;

    private String description;

    private String descriptionEnglish;

    private String createdTime;

    private String modifiedTime;

    private boolean deleted;

    private String responsibilities;

    private String qualifications;

    private String offer;

    private String area;

    private String title;

    private String experienceLevel;

    private String employer;

    private String image;

}
