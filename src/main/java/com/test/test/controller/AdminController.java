package com.test.test.controller;

import com.test.test.entity.User;
import com.test.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final UserService userService;
//    private final QuestionnaireService questionnaireService;
//    private final QuestionService questionService;
//    private final AnswerOptionService answerOptionService;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

//    @GetMapping("/admin/questionnaire/create")
//    public String createQuestionnaireForm(Questionnaire questionnaire) {
//        return "questionnaire/questionnaire-create";
//    }
//
//    @PostMapping("/admin/questionnaire/create")
//    public String createQuestionnaire(Questionnaire questionnaire, Model model) {
//        questionnaireService.saveQuestionnaire(questionnaire);
//        model.addAttribute("questionnaire", questionnaire);
//        return "question-create";
//    }
}
