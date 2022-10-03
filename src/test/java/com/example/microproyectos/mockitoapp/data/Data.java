package com.example.microproyectos.mockitoapp.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.microproyectos.mockitoapp.models.Exam;

public class Data {
    public final static List<Exam> EXAMS = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"),
            new Exam(7L, "Música"));
    public final static List<Exam> EXAMS_EMPTY = Collections.emptyList();
    public final static List<String> QUESTIONS = Arrays.asList("Color?", "Fecha?", "Hora?");
    public final static List<String> QUESTIONS_EMPTY = Collections.emptyList();

}