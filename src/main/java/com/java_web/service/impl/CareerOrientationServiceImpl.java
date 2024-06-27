package com.java_web.service.impl;

import com.java_web.dto.response.CareerOrientationResponse;
import com.java_web.dto.reuqest.JobDTO;
import com.java_web.model.Job;
import com.java_web.model.JobDetail;
import com.java_web.model.User;
import com.java_web.repository.JobDetailRepository;
import com.java_web.repository.JobRepository;
import com.java_web.repository.UserRepository;
import com.java_web.service.CareerOrientationService;
import com.java_web.service.SkillScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CareerOrientationServiceImpl implements CareerOrientationService {

    private final JobDetailRepository jobDetailRepository;
    private final SkillScoreService skillScoreService;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    @Override
    public Map<Integer, BigDecimal> calculateTheAppropriateScore(Integer userId) {
        Map<Integer, BigDecimal> points = new HashMap<>();
        List<JobDetail> jobDetails = jobDetailRepository.findAll();

        BigDecimal programmingSkill = skillScoreService.calculateProgrammingSkillPoint(userId);
        BigDecimal designSkill = skillScoreService.calculateDesignSkillPoint(userId);
        BigDecimal logicalThinking = skillScoreService.calculateLogicalThinkingPoint(userId);
        BigDecimal interactionSkill = skillScoreService.calculateInteractionSkillPoint(userId);

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
        Integer reliability = user.getStudyCredit().getReliability();

        for (JobDetail job : jobDetails) {
            BigDecimal score = BigDecimal.ZERO;
            score = score.add(programmingSkill.multiply(BigDecimal.valueOf(job.getCoding() / 100.0)));
            score = score.add(designSkill.multiply(BigDecimal.valueOf(job.getDesigning() / 100.0)));
            score = score.add(logicalThinking.multiply(BigDecimal.valueOf(job.getLogicalThinking() / 100.0)));
            score = score.add(interactionSkill.multiply(BigDecimal.valueOf(job.getInteract() / 100.0)));
            score = score.add(BigDecimal.valueOf(reliability).multiply(BigDecimal.valueOf(job.getReliability() / 100.0)));
            points.put(job.getId(), score);
        }

        BigDecimal maxValue = null;
        for (BigDecimal value : points.values()) {
            if (maxValue == null || value.compareTo(maxValue) > 0) {
                maxValue = value;
            }
        }

        Map<Integer, BigDecimal> orientationPoint = new HashMap<>();
        for (Map.Entry<Integer, BigDecimal> entry : points.entrySet()) {
            BigDecimal value = entry.getValue();
            if (maxValue.subtract(value).abs().compareTo(BigDecimal.valueOf(0.1)) <= 0) {
                orientationPoint.put(entry.getKey(), value);
            }
        }

        // Chuyển các mục của Map vào một danh sách
        List<Map.Entry<Integer, BigDecimal>> entryList = new ArrayList<>(orientationPoint.entrySet());

        // Sắp xếp danh sách theo giá trị từ lớn đến bé
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // Tạo một LinkedHashMap để duy trì thứ tự sắp xếp
        Map<Integer, BigDecimal> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, BigDecimal> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    @Override
    public List<CareerOrientationResponse> getJobsById(Map<Integer, BigDecimal> ids) {
        List<CareerOrientationResponse> jobs = new ArrayList<>();

        for (Integer id : ids.keySet()) {
            CareerOrientationResponse careerOrientationResponse = new CareerOrientationResponse();
            JobDTO jobDTO = mapToDTO(jobRepository.findById(id).get());
            JobDetail jobDetail = jobDetailRepository.findById(id).get();
            careerOrientationResponse.setJobDTO(jobDTO);
            careerOrientationResponse.setDesign(jobDetail.getDesigning());
            careerOrientationResponse.setCoding(jobDetail.getCoding());
            careerOrientationResponse.setLogic(jobDetail.getLogicalThinking());
            careerOrientationResponse.setInteract(jobDetail.getInteract());
            careerOrientationResponse.setReliability(jobDetail.getReliability());

            jobs.add(careerOrientationResponse);
        }

        return jobs;
    }

    public JobDTO mapToDTO(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setName(job.getName());
        jobDTO.setNameEnglish(job.getNameEnglish());
        jobDTO.setRequire(job.getRequire());
        jobDTO.setRequireEnglish(job.getRequireEnglish());
        jobDTO.setLinks(job.getLinks());
        jobDTO.setSkills(job.getSkills());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setDescriptionEnglish(job.getDescriptionEnglish());
        jobDTO.setImage(job.getImage());
        return jobDTO;
    }
}
