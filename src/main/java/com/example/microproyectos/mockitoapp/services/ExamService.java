package com.example.microproyectos.mockitoapp.services;


import com.example.microproyectos.mockitoapp.models.Exam;

public interface ExamService {
    Exam findExamByName(String name);
}
