package com.java_web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java_web.dto.reuqest.ScoreDTO;
import com.java_web.model.Score;
import com.java_web.model.User;
import com.java_web.repository.ScoreRepository;
import com.java_web.repository.UserRepository;
import com.java_web.service.ScoreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService{
	
	private ScoreRepository scoreRepository;
	private UserRepository userRepository;

	@Override
	public List<ScoreDTO> getScoreByUser(int userId) {
		List<ScoreDTO> list = new ArrayList<ScoreDTO>();
		User u = userRepository.findById(userId).get();
		List<Score> rawList = scoreRepository.findByUser(u);

		rawList.forEach(item -> {
			if (item.getScore() != null) {
				ScoreDTO i = new ScoreDTO();
				i.setCourseName(item.getCourseName());
				i.setScoreValue(item.getScore().floatValue());
				
				list.add(i);
			}
		});
		
		return list;
	}
	
}
