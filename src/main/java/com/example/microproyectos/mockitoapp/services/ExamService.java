package com.example.microproyectos.mockitoapp.services;


import java.util.Optional;

import com.example.microproyectos.mockitoapp.models.Exam;

public interface ExamService {
    Optional<Exam> findExamByName(String name);

    Exam FindExamWithQuestionsByName(String name);
}
