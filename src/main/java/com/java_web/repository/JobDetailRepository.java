package com.java_web.repository;

import com.java_web.model.Job;
import com.java_web.model.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDetailRepository extends JpaRepository<JobDetail, Integer> {
}
