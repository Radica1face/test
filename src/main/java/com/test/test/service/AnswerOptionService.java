package com.test.test.service;

import com.test.test.entity.AnswerOption;
import com.test.test.repository.AnswerOptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnswerOptionService {
    private final AnswerOptionRepository answerOptionRepository;

    public AnswerOption findById(Long id) {
        return answerOptionRepository.getById(id);
    }

    public void saveAnswerOption(AnswerOption answerOption) {
        answerOptionRepository.save(answerOption);
    }

    public void deleteAnswerOption(Long id) {
        answerOptionRepository.deleteById(id);
    }
}
