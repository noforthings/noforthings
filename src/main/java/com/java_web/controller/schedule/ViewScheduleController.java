package com.java_web.controller.schedule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java_web.dto.reuqest.ScoreDTO;
import com.java_web.dto.reuqest.SubjectDTO;
import com.java_web.model.Subject;
import com.java_web.service.ScoreService;
import com.java_web.service.SubjectService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewScheduleController {
	
	private ScoreService scoreService;
	private SubjectService subjectService;
	
	@GetMapping("/schedule")
	public String viewUserScore(Model model) {
		int userId = 2;
		
		List<ScoreDTO> list = scoreService.getScoreByUser(userId);
		model.addAttribute("scoreDetail", list);
		
		Map<String, List<SubjectDTO>> map = new HashMap<>();
		
		// Save list subject of each semester as map, if semester is empty, put expected subject instead
		for (int i=1; i<9; i++) {
			List<SubjectDTO> sub = subjectService.getStudy(userId, i);
			if (!sub.isEmpty()) {
				map.put("sub"+i, sub);
			} else {
				//Map<String, List<SubjectDTO>> otherChoices = new HashMap<String, List<Subject>>();
				sub = subjectService.getExpected(userId);
				map.put("sub"+i, sub);
				break;
			}
		}
		
		model.addAllAttributes(map);
		
		return "schedule";
	}
}
