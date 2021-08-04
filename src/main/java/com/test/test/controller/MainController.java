package com.test.test.controller;

import com.test.test.entity.*;
import com.test.test.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {

    private final QuestionnaireService questionnaireService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;
    private final UserService userService;
    private final AnswerOptionService answerOptionService;

    @GetMapping("/")
    public String main(Questionnaire questionnaire, Model model) {
        List<Questionnaire> questionnaires = questionnaireService.findAll();
        model.addAttribute("questionnaires", questionnaires);
        return "main";
    }

    @GetMapping("/questionnaire/{id}")
    public String showQuestionnaire(@PathVariable("id") Long id, Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        List<Question> questions = questionService.findByQuestionnaireId(id);
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questions);
        return "question/questions";
    }

    @PostMapping("/questionnaire")
    public String createChoice(@RequestParam("selected") Long[] selected,
                               @RequestParam("username") String username) {

        User user = userService.findByUsername(username);

        for(Long answerId: selected) {
            AnswerOption answerOption = answerOptionService.findById(answerId);
            Question question = answerOption.getQuestion();
            Choice choice = new Choice();
            choice.setUser(user);
            choice.setAnswerOption(answerOption);
            choice.setQuestion(question);
            choiceService.saveChoice(choice);
        }
        return "success";
    }
}
