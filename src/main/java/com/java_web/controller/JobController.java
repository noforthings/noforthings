package com.java_web.controller;

import com.java_web.dto.response.JobResponse;
import com.java_web.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/jobs")
    public String getJobs(Model model,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<JobResponse> jobPage = jobService.getJobs(page, size);
        model.addAttribute("jobPage", jobPage);
        return "job"; // Tên của view template để hiển thị danh sách công việc
    }
}


