package com.test.test.service;

import com.test.test.entity.Question;
import com.test.test.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question findById(Long id) {
        return questionRepository.getById(id);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findByQuestionnaireId(Long questionnaireId) {
        return questionRepository.findByQuestionnaireId(questionnaireId);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
