package com.java_web.dto.reuqest;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDetailDTO {
    private Integer id;
    private int coding;
    private int designing;
    private int logicalThinking;
    private int interact;
    private int reliability;
    private String detailCreatedTime;
    private String detailModifiedTime;
}
