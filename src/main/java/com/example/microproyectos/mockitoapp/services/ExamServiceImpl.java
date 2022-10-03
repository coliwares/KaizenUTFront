package com.example.microproyectos.mockitoapp.services;

import java.util.List;
import java.util.Optional;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.QuestionRepository;

public class ExamServiceImpl implements ExamService {
    ExamRepository examRepository;
    QuestionRepository  questionRepository;

    public  ExamServiceImpl(ExamRepository  examRepository, QuestionRepository  questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {
        return examRepository.findAll().stream()
                .filter(e -> e.getName().contains(name))
                .findFirst();
    }

    @Override
    public Exam FindExamWithQuestionsByName(String name) {
        Optional<Exam> examOptional = findExamByName(name);
        Exam  exam = null;
        if (examOptional.isPresent()) {
            exam = examOptional.get();
            List<String> questions = questionRepository.findQuestionsbyId(exam.getId());
            exam.setQuestions(questions);
        }
        return  exam;
    }

}
