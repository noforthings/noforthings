package com.java_web.dto.response;

import com.java_web.dto.reuqest.JobDTO;
import com.java_web.dto.reuqest.JobDetailDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobResponse {
    private JobDTO jobDTO;
    private JobDetailDTO jobDetailDTO;
}
