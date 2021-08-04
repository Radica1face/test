package com.test.test.controller.admin;

import com.test.test.entity.AnswerOption;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionController {

    private final QuestionnaireService questionnaireService;
    private final QuestionService questionService;
    private final AnswerOptionService answerOptionService;
    private final QuestionRepository questionRepository;

    @GetMapping("/admin/question/create/{id}")
    public String createQuestionForm(@PathVariable("id") Long id, Question question, Model model) {
        Questionnaire questionnaire = questionnaireService.findById(id);
        model.addAttribute("questionnaire", questionnaire);
        return "question/question-create";
    }

    @PostMapping("/admin/question/create")
    public String createQuestion(@RequestParam("questionnaireId") Long questionnaireId,
                                 @RequestParam("questionText") String questionText,
                                 @RequestParam("answerOption") String[] answerOptions,
                                 RedirectAttributes redirectAttributes) {
        Questionnaire questionnaire = questionnaireService.findById(questionnaireId);

        Question question = new Question();
        question.setQuestionText(questionText);
        question.setQuestionnaire(questionnaire);
        questionService.saveQuestion(question);
        questionRepository.flush();

        for(String answerText: answerOptions){
            AnswerOption answerOption = new AnswerOption();
            answerOption.setAnswerText(answerText);
            answerOption.setQuestion(question);
            answerOptionService.saveAnswerOption(answerOption);
        }
        redirectAttributes.addAttribute("id", questionnaire.getId());
        return "redirect:/admin/questionnaire/update/{id}";
    }

    @GetMapping("/admin/question/update/{id}")
    public String updateQuestionForm(@PathVariable("id") Long id, Model model) {
        Question question = questionService.findById(id);
        List<AnswerOption> answerOptions = question.getAnswerOptions();
        model.addAttribute("answerOptions", answerOptions);
        model.addAttribute("question", question);
        return "question/question-update";
    }

    @PostMapping("/admin/question/update")
    public String updateQuestion(@RequestParam("id") Long id,
                                 @RequestParam("questionText") String questionText,
                                 RedirectAttributes redirectAttributes) {
        Question question = questionService.findById(id);
        question.setQuestionText(questionText);
        questionService.saveQuestion(question);
        redirectAttributes.addAttribute("id", question.getId());
        return "redirect:/admin/question/update/{id}";
    }
}
