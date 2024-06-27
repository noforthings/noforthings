package com.java_web.service.impl;

import com.java_web.model.Score;
import com.java_web.model.User;
import com.java_web.repository.ScoreRepository;
import com.java_web.repository.SubjectRepository;
import com.java_web.repository.UserRepository;
import com.java_web.service.SkillScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillScoreServiceImpl implements SkillScoreService {

    private final SubjectRepository subjectRepository;
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    @Override
    public BigDecimal calculateProgrammingSkillPoint(Integer id) {
        User user = userRepository.findById(id).get();
        List<Score> scores = user.getScores();
        BigDecimal programmingSkill = BigDecimal.ZERO; // Khởi tạo totalScore với giá trị ban đầu là 0
        int totalWeight = 0;
        for (Score score : scores) {
            int weight = score.getSubject().getCoding();
            totalWeight += weight;
            if (score.getScore() != null) {
                BigDecimal scoreValue = score.getScore().multiply(BigDecimal.valueOf(weight)); // Tính giá trị điểm số nhân với trọng số
                programmingSkill = programmingSkill.add(scoreValue); // Cộng dồn vào programmingSkill
            }
        }

        if (totalWeight != 0) {
            programmingSkill = programmingSkill.divide(BigDecimal.valueOf(totalWeight), BigDecimal.ROUND_HALF_UP); // Tính trung bình điểm theo trọng số
        }
        return programmingSkill;
    }

    @Override
    public BigDecimal calculateDesignSkillPoint(Integer id) {
        User user = userRepository.findById(id).get();
        List<Score> scores = user.getScores();
        BigDecimal designSkill = BigDecimal.ZERO; // Khởi tạo totalScore với giá trị ban đầu là 0
        int totalWeight = 0;
        for (Score score : scores) {
            int weight = score.getSubject().getDesigning();
            totalWeight += weight;
            if (score.getScore() != null) {
                BigDecimal scoreValue = score.getScore().multiply(BigDecimal.valueOf(weight)); // Tính giá trị điểm số nhân với trọng số
                designSkill = designSkill.add(scoreValue); // Cộng dồn vào programmingSkill
            }
        }

        if (totalWeight != 0) {
            designSkill = designSkill.divide(BigDecimal.valueOf(totalWeight), BigDecimal.ROUND_HALF_UP); // Tính trung bình điểm theo trọng số
        }
        return designSkill;

    }

    @Override
    public BigDecimal calculateLogicalThinkingPoint(Integer id) {
        User user = userRepository.findById(id).get();
        List<Score> scores = user.getScores();
        BigDecimal logicalThinking = BigDecimal.ZERO; // Khởi tạo totalScore với giá trị ban đầu là 0
        int totalWeight = 0;
        for (Score score : scores) {
            int weight = score.getSubject().getLogicalThinking();
            totalWeight += weight;
            if (score.getScore() != null) {
                BigDecimal scoreValue = score.getScore().multiply(BigDecimal.valueOf(weight)); // Tính giá trị điểm số nhân với trọng số
                logicalThinking = logicalThinking.add(scoreValue); // Cộng dồn vào programmingSkill
            }
        }

        if (totalWeight != 0) {
            logicalThinking = logicalThinking.divide(BigDecimal.valueOf(totalWeight), BigDecimal.ROUND_HALF_UP); // Tính trung bình điểm theo trọng số
        }
        return logicalThinking;
    }

    @Override
    public BigDecimal calculateInteractionSkillPoint(Integer id) {
        User user = userRepository.findById(id).get();
        List<Score> scores = user.getScores();
        BigDecimal interactionSkill = BigDecimal.ZERO; // Khởi tạo totalScore với giá trị ban đầu là 0
        int totalWeight = 0;
        for (Score score : scores) {
            int weight = score.getSubject().getLogicalThinking();
            totalWeight += weight;
            if (score.getScore() != null) {
                BigDecimal scoreValue = score.getScore().multiply(BigDecimal.valueOf(weight)); // Tính giá trị điểm số nhân với trọng số
                interactionSkill = interactionSkill.add(scoreValue); // Cộng dồn vào programmingSkill
            }
        }

        if (totalWeight != 0) {
            interactionSkill = interactionSkill.divide(BigDecimal.valueOf(totalWeight), BigDecimal.ROUND_HALF_UP); // Tính trung bình điểm theo trọng số
        }
        return interactionSkill;
    }
}
