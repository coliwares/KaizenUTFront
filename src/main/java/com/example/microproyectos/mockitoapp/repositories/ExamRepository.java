package com.example.microproyectos.mockitoapp.repositories;

import java.util.List;

import com.example.microproyectos.mockitoapp.models.Exam;

public interface ExamRepository {
    List<Exam> findAll();
    Exam save(Exam exam);
}
