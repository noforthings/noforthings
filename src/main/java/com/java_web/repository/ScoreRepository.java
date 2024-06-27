package com.java_web.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java_web.model.Score;
import com.java_web.model.Subject;
import com.java_web.model.User;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
	List<Score> findByUser(User user);
	@Query(value="SELECT * FROM tblscore WHERE user_id = ?1 AND sub_id = ?2", nativeQuery = true)
	Score findBySub(int userId, int subId);
}
