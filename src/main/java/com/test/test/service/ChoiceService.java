package com.test.test.service;

import com.test.test.entity.Choice;
import com.test.test.repository.ChoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChoiceService {
    private final ChoiceRepository choiceRepository;

    public void saveChoice(Choice question) {
        choiceRepository.save(question);
    }

    public List<Choice> findByUserId(Long userId) {
        return choiceRepository.findByUserId(userId);
    }
}
