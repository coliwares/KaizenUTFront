package com.example.microproyectos.mockitoapp.services;

import java.util.Optional;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;

public class ExamServiceImpl implements ExamService {
    ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Optional<Exam> findExamByName(String name) {
        return examRepository.findAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .findFirst();
    }

}
