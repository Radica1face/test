package com.test.test.controller.admin;

import com.test.test.entity.Question;
import com.test.test.entity.Questionnaire;
import com.test.test.repository.QuestionRepository;
import com.test.test.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @GetMapping("/admin/questionnaires")
    public String allQuestionnaires(Model model) {
        List<Questionnaire> questionnaires = questionnaireService.findAll();
        model.addAttribute("questionnaires", questionnaires);
        return "questionnaire/questionnaires";
    }

    @GetMapping("/admin/questionnaire/create")
    public String createQuestionnaireForm(Questionnaire questionnaire) {
        return "questionnaire/questionnaire-create";
    }

    @PostMapping("/admin/questionnaire/create")
    public String createQuestionnaire(Questionnaire questionnaire) {
        questionnaireService.saveQuestionnaire(questionnaire);
        return "redirect:/admin/questionnaires";
    }

    @GetMapping("/admin/questionnaire/update/{id}")
    public String updateQuestionnaireForm(@PathVariable("id") Long id, Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        List<Question> questions = questionnaire.getQuestions();
        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("questions", questions);
        return "questionnaire/questionnaire-update";
    }

    @PostMapping("/admin/questionnaire/update")
    public String updateQuestionnaire(Questionnaire questionnaire, Model model) {
        questionnaireService.saveQuestionnaire(questionnaire);
        return "redirect:/admin/questionnaires";
    }
}
