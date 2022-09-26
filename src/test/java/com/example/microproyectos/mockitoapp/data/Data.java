package com.example.microproyectos.mockitoapp.data;

import java.util.Arrays;
import java.util.List;

import com.example.microproyectos.mockitoapp.models.Exam;

public class Data {
    public final static List<Exam> EXAMS = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
    public final static List<String> QUESTIONS = Arrays.asList("prugunta 1", "2 + 2 =", "integral derivada....");
    public final static Exam EXAM_TO_SAVE = new Exam(null, "Matemáticas");
    public final static List<Exam> EXAMS_ID_NULL = Arrays.asList(new Exam(null, "Matemáticas"), new Exam(null, "Lenguaje"), new Exam(null, "Música"));
}
