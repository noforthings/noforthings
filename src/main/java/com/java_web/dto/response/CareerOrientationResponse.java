package com.java_web.dto.response;

import com.java_web.dto.reuqest.JobDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerOrientationResponse {
    private JobDTO jobDTO;
    private Integer logic;
    private Integer coding;
    private Integer design;
    private Integer interact;
    private Integer reliability;
}
