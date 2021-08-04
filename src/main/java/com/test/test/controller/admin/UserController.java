package com.test.test.controller.admin;

import com.test.test.entity.Choice;
import com.test.test.entity.Question;
import com.test.test.entity.Questionnaire;
import com.test.test.entity.User;
import com.test.test.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ChoiceService choiceService;

    @GetMapping("/admin/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/users";
    }

    @GetMapping("/admin/user-questionnaires/{user_id}")
    public String userQuestionnaires(@PathVariable("user_id") Long userId, RedirectAttributes redirectAttributes, Model model) {
        User user = userService.findById(userId);
        List<Choice> choices = choiceService.findByUserId(userId);

        Set<Question> questions = new HashSet<Question>();
        for(Choice choice: choices) {
            Question question = choice.getQuestion();
            questions.add(question);
        }

        Set<Questionnaire> questionnaires = new HashSet<Questionnaire>();
        for(Question question: questions){
            Questionnaire questionnaire = question.getQuestionnaire();
            questionnaires.add(questionnaire);
        }

        model.addAttribute("choices", choices);
        model.addAttribute("questionnaires", questionnaires);
        return "user/user-questionnaires";
    }
}
