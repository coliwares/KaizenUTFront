package com.example.microproyectos.mockitoapp.repositories;

import java.util.Arrays;
import java.util.List;

import com.example.microproyectos.mockitoapp.models.Exam;

public class ExamRepositoryImpl implements ExamRepository {

    @Override
    public List<Exam> findAll() {
        // Test fail kata2 -- return Collections.emptyList();
        return Arrays.asList(new Exam(1L, "Matemáticas"),new Exam(3L, "Lenguaje"),new Exam(7L, "Música"));
    }

}
