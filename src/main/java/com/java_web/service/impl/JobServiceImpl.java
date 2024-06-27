package com.java_web.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java_web.dto.response.JobResponse;
import com.java_web.dto.reuqest.JobDTO;
import com.java_web.dto.reuqest.JobDetailDTO;
import com.java_web.dto.reuqest.StatDTO;
import com.java_web.model.Job;
import com.java_web.model.JobDetail;
import com.java_web.repository.JobDetailRepository;
import com.java_web.repository.JobRepository;
import com.java_web.service.JobService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{
	
	private final JobRepository jobRepository;
	
	public Job mapToEntity(JobDTO jobDTO) {
		Job job = new Job();
		
		job.setId(jobDTO.getId());
		job.setName(jobDTO.getName());
		job.setRequire(jobDTO.getRequire());
		job.setDescription(jobDTO.getDescription());
		job.setCreatedTime(jobDTO.getCreatedTime());
		job.setModifiedTime(jobDTO.getModifiedTime());
		job.setSkills(jobDTO.getSkills());
		job.setResponsibilities(jobDTO.getResponsibilities());
		job.setQualifications(jobDTO.getQualifications());
		job.setOffer(jobDTO.getOffer());
		job.setArea(jobDTO.getArea());
		job.setTitle(jobDTO.getTitle());
		job.setExperienceLevel(jobDTO.getExperienceLevel());
		job.setLinks(jobDTO.getLinks());
		job.setEmployer(jobDTO.getEmployer());
		job.setImage(jobDTO.getImage());
		
		return job;
	}
	
	public JobDTO mapToDTO(Job job) {
		JobDTO jobDTO = new JobDTO();
		
		jobDTO.setId(job.getId());
		jobDTO.setName(job.getName());
		jobDTO.setRequire(job.getRequire());
		jobDTO.setDescription(job.getDescription());
		jobDTO.setCreatedTime(job.getCreatedTime());
		jobDTO.setModifiedTime(job.getModifiedTime());
		jobDTO.setSkills(job.getSkills());
		jobDTO.setResponsibilities(job.getResponsibilities());
		jobDTO.setQualifications(job.getQualifications());
		jobDTO.setOffer(job.getOffer());
		jobDTO.setArea(job.getArea());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setExperienceLevel(job.getExperienceLevel());
		jobDTO.setLinks(job.getLinks());
		jobDTO.setEmployer(job.getEmployer());
		jobDTO.setImage(job.getImage());
		
		return jobDTO;
	}
	
	@Override
	public JobDTO getById(int id) {
		Job job = jobRepository.findById(id).get();
		JobDTO rjob = new JobDTO();
		if (job != null) {
			rjob = mapToDTO(job);
		}
		return rjob;
	}
	
	@Override
	public List<JobDTO> getAllJob() {
		List<JobDTO> list = new ArrayList<JobDTO>();
		List<Job> rawList = jobRepository.findAll();
		
		rawList.forEach(i -> {
			list.add(mapToDTO(i));
		});
				
		return list;
	}
	@Override
	public boolean deleteJob(int id) {
		if (jobRepository.existsById(id)) {
			jobRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
	@Override
	public boolean addJob(JobDTO jobDTO) {
		Job job = mapToEntity(jobDTO);
		if (jobRepository.save(job)!=null) {
				return true;
		}

		return false;
	}
	@Override
	public boolean updateJob(JobDTO jobDTO) {
		Job job = jobRepository.getReferenceById(jobDTO.getId());
		
		job.setId(jobDTO.getId());
		job.setName(jobDTO.getName());
		job.setRequire(jobDTO.getRequire());
		job.setDescription(jobDTO.getDescription());
		job.setCreatedTime(jobDTO.getCreatedTime());
		job.setModifiedTime(jobDTO.getModifiedTime());
		job.setSkills(jobDTO.getSkills());
		job.setResponsibilities(jobDTO.getResponsibilities());
		job.setQualifications(jobDTO.getQualifications());
		job.setOffer(jobDTO.getOffer());
		job.setArea(jobDTO.getArea());
		job.setTitle(jobDTO.getTitle());
		job.setExperienceLevel(jobDTO.getExperienceLevel());
		job.setLinks(jobDTO.getLinks());
		job.setEmployer(jobDTO.getEmployer());
		job.setImage(jobDTO.getImage());
		
		jobRepository.save(job);
		
		if (jobRepository.save(job)!=null) {
			return true;
		}
	
		return false;
	}
	@Override
	public List<JobDTO> getJobBySkills(String skills) {
		List<Job> list = jobRepository.findBySkills(skills);
		List<JobDTO> listDTO = new ArrayList<JobDTO>();
		
		list.forEach(i -> {
			listDTO.add(mapToDTO(i));
		});
		
		return listDTO;
	}
	
	@Override
	public boolean saveImage(MultipartFile file, JobDTO job)	{
		try {
			String fileName = "";
			
			if (file != null) {
				String uploadDir = "src/main/resources/static/admin/img/job";
				if (!Files.exists(Paths.get(uploadDir))) {
		            Files.createDirectories(Paths.get(uploadDir)); //Created dir if not exist
		        }
				fileName = file.getOriginalFilename();
		        Path filePath = Paths.get(uploadDir).resolve(fileName);
//		        System.out.println(filePath.toString());
		        Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}
	        
	        job.setImage(fileName);
	        
	        return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return false;
	}
    private final JobRepository statisticRepository;
    private final JobDetailRepository jobDetailRepository;

    @Override
    public List<StatDTO> getAvgScore() {
        List<StatDTO> list = new ArrayList<StatDTO>();

        List<Object[]> rawList = statisticRepository.getAvgScore();
        for (Object[] rawItem : rawList) {

            int id = (Integer) rawItem[0];
            String name = (String) rawItem[1];
            BigDecimal rscore = (BigDecimal) rawItem[2];
            float score = rscore.floatValue();
            Long rtotal = (Long) rawItem[3];
            int total = rtotal.intValue();

            StatDTO item = new StatDTO(id, name, score, total);

            list.add(item);
        }

        return list;
    }

    public Page<JobResponse> getJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = statisticRepository.findAll(pageable);

        return jobPage.map(this::convertToJobResponse);
    }

    private JobResponse convertToJobResponse(Job job) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setSkills(job.getSkills());
        jobDTO.setName(job.getName());
        jobDTO.setLinks(job.getLinks());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setRequire(job.getRequire());
        // Gán các thuộc tính khác của JobDTO

        JobDetail jobDetail = jobDetailRepository.findById(job.getId()).get();
        System.out.println(jobDetail);
        JobDetailDTO jobDetailDTO = new JobDetailDTO();
        if (jobDetail != null) {
            jobDetailDTO.setId(jobDetail.getId());
            jobDetailDTO.setCoding(jobDetail.getCoding());
            jobDetailDTO.setInteract(jobDetail.getInteract());
            jobDetailDTO.setReliability(jobDetail.getReliability());
            jobDetailDTO.setDesigning(jobDetail.getDesigning());
            jobDetailDTO.setLogicalThinking(jobDetail.getLogicalThinking());
            // Gán các thuộc tính khác của JobDetailDTO
        }
        System.out.println(jobDetailDTO.getId());

        JobResponse jobResponse = new JobResponse();
        jobResponse.setJobDTO(jobDTO);
        jobResponse.setJobDetailDTO(jobDetailDTO);

        return jobResponse;
    }
}
