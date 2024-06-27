package com.java_web.service;

import java.util.List;

import com.java_web.dto.reuqest.ScoreDTO;
import com.java_web.model.Score;
import com.java_web.model.User;

public interface ScoreService {
	public List<ScoreDTO> getScoreByUser(int UserId);
}
