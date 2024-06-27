package com.java_web.service;

import java.util.List;
import java.util.Map;

import com.java_web.dto.reuqest.SubjectDTO;
import com.java_web.model.Subject;

public interface SubjectService {
	public List<SubjectDTO> getStudy(int userId, int semester);
	public List<SubjectDTO> getExpected(int userId);
}
