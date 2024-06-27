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
public class UpdateJobController {
	private JobService jobService;
	
	@GetMapping("/admin/job/edit")
	public String edit(@RequestParam(value="id", required=false) int id, Model model) {
		JobDTO editJob = new JobDTO();
		if (id!=0) {
			editJob = jobService.getById(id);
		}
		model.addAttribute("editJob", editJob);
		model.addAttribute("newEditJob", editJob);
		
		return "admin/tables-job-edit";
	}
	
	@PostMapping("/admin/job/edit")
	public String editJob(@ModelAttribute("newEditJob") JobDTO jobDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, 
			@RequestParam(name="jobImg", required = false) MultipartFile jobImg) {
		if (bindingResult.hasErrors() && jobDTO.getId()==null) {
			redirectAttributes.addFlashAttribute("editResponse", false);
			return "admin/tables-job-edit";
		} else
			try {
				if (jobImg != null) {
					jobDTO.setImage("");
				}
				if (jobService.saveImage(jobImg, jobDTO)) {
					if (jobService.updateJob(jobDTO)){
						redirectAttributes.addFlashAttribute("editResponse", true);
					} 
					else {
						redirectAttributes.addFlashAttribute("editResponse", false);
					}
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		return "redirect:/admin/job/edit?id=0";
	}
	
	@PostMapping("/admin/job/del")
	public String deleteJob(@RequestParam int id, RedirectAttributes redirectAttributes) {
		if (jobService.deleteJob(id)) {
			redirectAttributes.addFlashAttribute("delResponse", true);
		} else {
			redirectAttributes.addFlashAttribute("delResponse", false);
		}
		return "redirect:/admin/job";
	}
}
