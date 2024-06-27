package com.java_web.repository;

import com.java_web.model.StudyCredit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudyCreditRepository extends CrudRepository<StudyCredit, Integer> {
    List<StudyCredit> findAll();
}
