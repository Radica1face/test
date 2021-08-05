package com.test.test.service;

import com.test.test.entity.Questionnaire;
import com.test.test.repository.QuestionnaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    public Questionnaire findById(Long id) {
        return questionnaireRepository.getById(id);
    }

    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    public void saveQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

    public void deleteQuestionnaire(Long id) {
        questionnaireRepository.deleteById(id);
    }
}
