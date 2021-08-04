package com.test.test.controller.admin;

import com.test.test.entity.AnswerOption;
import com.test.test.entity.Question;
import com.test.test.entity.Questionnaire;
import com.test.test.repository.QuestionRepository;
import com.test.test.service.AnswerOptionService;
import com.test.test.service.QuestionService;
import com.test.test.service.QuestionnaireService;
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
public class AnswerOptionController {

    private final QuestionnaireService questionnaireService;
    private final QuestionService questionService;
    private final AnswerOptionService answerOptionService;
    private final QuestionRepository questionRepository;

    @GetMapping("/admin/answer/create/{id}")
    public String createAnswerForm(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
        return "answer/answer-create";
    }

    @PostMapping("/admin/answer/create")
    public String createAnswer(@RequestParam("questionId") Long questionId,
                               @RequestParam("answerText") String answerText,
                               RedirectAttributes redirectAttributes) {
        Question question = questionService.findById(questionId);
        AnswerOption answerOption = new AnswerOption();
        answerOption.setAnswerText(answerText);
        answerOption.setQuestion(question);
        answerOptionService.saveAnswerOption(answerOption);
        redirectAttributes.addAttribute("id", question.getId());
        return "redirect:/admin/question/update/{id}";
    }

    @GetMapping("/admin/answer/update/{id}")
    public String updateAnswerForm(@PathVariable("id") Long id, Model model) {
        AnswerOption answerOption = answerOptionService.findById(id);
        model.addAttribute("answerOption", answerOption);
        return "answer/answer-update";
    }

    @PostMapping("/admin/answer/update")
    public String updateAnswer(@RequestParam("id") Long id,
                               @RequestParam("answerText") String answerText,
                               RedirectAttributes redirectAttributes) {
        AnswerOption answerOption = answerOptionService.findById(id);
        answerOption.setAnswerText(answerText);
        answerOptionService.saveAnswerOption(answerOption);
        Question question = answerOption.getQuestion();
        redirectAttributes.addAttribute("id", question.getId());
        return "redirect:/admin/question/update/{id}";
    }
}
