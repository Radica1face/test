package com.test.test.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne ()
    @JoinColumn (name="answer_id")
    private AnswerOption answerOption;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne ()
    @JoinColumn (name="user_id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="question_id")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerOption getAnswerOption() {
        return answerOption;
    }

    public void setAnswerOption(AnswerOption answerOption) {
        this.answerOption = answerOption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
