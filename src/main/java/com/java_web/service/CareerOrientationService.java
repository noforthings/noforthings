package com.java_web.service;

import com.java_web.dto.response.CareerOrientationResponse;
import com.java_web.model.Job;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CareerOrientationService {
    public Map<Integer, BigDecimal> calculateTheAppropriateScore(Integer userId);
    public List<CareerOrientationResponse> getJobsById(Map<Integer, BigDecimal> ids);


}
