package com.java_web.controller.Job;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java_web.dto.reuqest.JobDTO;
import com.java_web.service.JobService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@AllArgsConstructor
public class ViewJobController {
	private JobService jobService;
	
	@GetMapping("/admin/job")
	public String view(Model model) {
		List<JobDTO> jobList = jobService.getAllJob();
		if (!jobList.isEmpty()) {
			model.addAttribute("jobList", jobList);
		} else {
			model.addAttribute("message", "Table job is empty.");
		}
		
		return "admin/tables-job";
	}
	
	@GetMapping("/job")
	public String viewJobUser(Model model) {
		List<JobDTO> jobList = jobService.getAllJob();
		if (!jobList.isEmpty()) {
			model.addAttribute("jobList", jobList);
		} else {
			model.addAttribute("message", "Table job is empty.");
		}
		
		return "job";
	}
}
