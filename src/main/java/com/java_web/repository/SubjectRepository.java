package com.java_web.repository;

import com.java_web.model.Subject;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	@Query(value = "SELECT tblsubject.* FROM tblsubject LEFT JOIN tblscore ON tblscore.sub_id = tblsubject.sub_id AND tblscore.user_id = ?1 WHERE tblscore.sub_id IS NULL AND tblsubject.sub_id > 0;", nativeQuery = true)
	public List<Subject> findNotStudy(int userId);
	@Query(value="SELECT sj.* FROM tblsubject sj INNER JOIN tblscore sc ON sc.sub_id = sj.sub_id WHERE  sc.user_id = ?1 AND sj.smt_id = ?2", nativeQuery = true)
	public List<Subject> findStudy(int userId, int semester);
}
