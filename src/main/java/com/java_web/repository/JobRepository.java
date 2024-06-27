package com.java_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java_web.dto.reuqest.JobDTO;
import com.java_web.model.Job;
import java.util.List;


public interface JobRepository extends JpaRepository<Job, Integer>{
	@Query(value = "SELECT * FROM tbljob WHERE job_skills LIKE %:s1%", nativeQuery = true)
	List<Job> findBySkills(@Param("s1") String skills);
	@Query(value = "SELECT * FROM tbljob WHERE job_skills LIKE %:skill1% AND job_skills LIKE %:skill2%", nativeQuery = true)
	List<Job> findBySkillv2(@Param("skill1") String skill1, @Param("skill2") String skill2);

    @Query(value="select s.user_id as id, "
            + "u.user_fullname as name, "
            + "avg(s.score_value) as score, "
            + "count(s.score_id) as total "
            + "from tblscore s inner join tbluser u on s.user_id = u.user_id "
            + "where s.score_status = 'done' "
            + "group by s.user_id;", nativeQuery = true)
    public List<Object[]> getAvgScore();

}
