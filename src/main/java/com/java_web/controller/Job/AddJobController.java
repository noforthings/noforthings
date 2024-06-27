package com.java_web.controller.Job;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.java_web.dto.reuqest.JobDTO;
import com.java_web.service.JobService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AddJobController {
	
	private JobService jobService;
	
	@GetMapping("/admin/job/add")
	public String afterAddJob(Model model) {
		model.addAttribute("newJob", new JobDTO());
		return "admin/tables-job-add";
	}
	@PostMapping("/admin/job/add")
	public String addJob(@ModelAttribute("newJob") JobDTO newJob, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestParam(name="jobImg", required = false) MultipartFile jobImg) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("addResponse", false);
			return "admin/tables-job-add";
		}
		try {
			if (jobImg != null) {
				newJob.setImage("");
			}
			if (jobService.saveImage(jobImg, newJob)) {
				System.out.println("> Save image: done");
				if (jobService.addJob(newJob)) {
					redirectAttributes.addFlashAttribute("addResponse", true);
				} else {
					redirectAttributes.addFlashAttribute("addResponse", false);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/admin/job/add";
	}
	
	@PostMapping("/admin/job/addfile")
	public ResponseEntity<String> addJobFromFile(@RequestParam("jobImgFile") MultipartFile jobFile) {
		return ResponseEntity.ok("Add from file successfully!");
	}
}
