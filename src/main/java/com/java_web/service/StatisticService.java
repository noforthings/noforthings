package com.java_web.service;

import java.util.List;
import java.util.Map;

import com.java_web.dto.reuqest.StatDTO;

public interface StatisticService {
	public List<StatDTO> getAvgScore();
	public Map<Integer, Long> getTodoPerMonth();
	public Long getTotalUser();
	public Long getTotalJob();
	public Float getAvgTotal();
}
