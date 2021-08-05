package com.test.test.repository;

import com.test.test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM questions WHERE questionnaire_id = ?1", nativeQuery = true)
    List<Question> findByQuestionnaireId(Long questionnaireId);
}
