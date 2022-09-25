package com.example.microproyectos.mockitoapp.repositories;

import java.util.Collections;
import java.util.List;

import com.example.microproyectos.mockitoapp.models.Exam;

public class ExamRepositoryImpl implements ExamRepository {

    @Override
    public List<Exam> findAll() {
        // /* KATA2 Test fail */  return Collections.emptyList();
        // /* KATA2 Test OK */  return Arrays.asList(new Exam(1L, "Matemáticas"),new Exam(3L, "Lenguaje"),new Exam(7L, "Música"));
        /* KATA3 No dependemos de la implementacion */return Collections.emptyList();
    }

}
