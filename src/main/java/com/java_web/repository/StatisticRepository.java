package com.java_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java_web.dto.reuqest.StatDTO;
import com.java_web.model.Job;

public interface StatisticRepository extends JpaRepository<Job, Integer>{
	
	@Query(value="select s.user_id as id, "
			+ "u.user_fullname as name, "
			+ "avg(s.score_value) as score, "
			+ "count(s.score_id) as total "
			+ "from tblscore s inner join tbluser u on s.user_id = u.user_id "
			+ "where s.score_status = 'done' "
			+ "group by s.user_id;", nativeQuery = true)
	public List<Object[]> getAvgScore();
	
	@Query(value="SELECT count(t.todo_id) as total, month(t.todo_created_time) as month FROM tbltodolist t inner join tbluser u on t.user_id = u.user_id WHERE year(t.todo_created_time) = year(curdate()) GROUP BY month", nativeQuery = true)
	public List<Object[]> getTodoPerMonth();
	
	@Query(value="SELECT count(*) FROM tbluser", nativeQuery = true)
	public List<Object[]> findTotalUser();
	@Query(value="SELECT count(*) FROM tbljob", nativeQuery = true)
	public List<Object[]> findTotalJob();
	@Query(value="SELECT round(avg(score_value), 2) FROM tblscore", nativeQuery = true)
	public List<Object[]> findAvgTotal();
}
