package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.ExamRepositoryImpl;

public class ExamServiceImplTest {
    @Test
    @DisplayName("should test method service using repository Implement")
    @Disabled
    void testKata2_a() {
        ExamRepository examRepository = new ExamRepositoryImpl();
        ExamService examService = new ExamServiceImpl(examRepository);
        Exam exam = examService.findExamByName("Matemáticas");

        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        
    }

    @Test
    @DisplayName("should test method service using Mocks")
    void testKata3() {
        ExamRepository examRepository = mock(ExamRepository.class);
        ExamService examService = new ExamServiceImpl(examRepository);
        List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"),new Exam(3L, "Lenguaje"),new Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);

        Exam exam = examService.findExamByName("Matemáticas");

        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        
    }

}
