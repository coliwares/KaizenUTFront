package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.ExamRepositoryImpl;

public class ExamServiceImplTest {
    @Test
    @DisplayName("should test method service using repository Implement")
    void testKata2_a() {
        ExamRepository examRepository = new ExamRepositoryImpl();
        ExamService examService = new ExamServiceImpl(examRepository);
        Exam exam = examService.findExamByName("Matemáticas");

        assertNotNull(exam);
        assertEquals(1L, exam.getId());
        
    }


}
