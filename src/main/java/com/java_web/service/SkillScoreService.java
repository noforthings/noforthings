package com.java_web.service;

import com.java_web.model.Score;
import com.java_web.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface SkillScoreService {
    public BigDecimal calculateProgrammingSkillPoint(Integer id);
    public BigDecimal calculateDesignSkillPoint(Integer id);
    public BigDecimal calculateLogicalThinkingPoint(Integer id);
    public BigDecimal calculateInteractionSkillPoint(Integer id);
}
