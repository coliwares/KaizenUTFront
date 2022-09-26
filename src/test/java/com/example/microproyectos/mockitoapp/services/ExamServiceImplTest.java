package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;

public class ExamServiceImplTest {
    
/*     @Test
    @DisplayName("Crear un test utilizando la implementación del repositorio")
    void Kata2() {

        ExamRepository examRepository =  new ExamRepositoryImpl();
        ExamService examService  =  new ExamServiceImpl(examRepository); 

        Exam exam = examService.findExamByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        

    }

    @Test
    void Kata2_a() {

        ExamRepository examRepository =  new ExamRepositoryImpl();
        ExamService examService  =  new ExamServiceImpl(examRepository); 

        Exam exam = examService.findExamByName("Matemáticas");

        assertNull(exam);

    } */

/*     @Test
    @DisplayName("Modificar el test creado utilizando Mockito")
    void Kata2() {

        ExamRepository examRepository =  mock(ExamRepository.class);
        ExamService examService  =  new ExamServiceImpl(examRepository); 
        List<Exam> exams = Arrays.asList(new  Exam(1L, "Matemáticas"), new  Exam(3L, "Lenguaje"), new  Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);

        Exam exam = examService.findExamByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(1L, exam.getId());
        

    }

    @Test
    void Kata2_a() {

        ExamRepository examRepository =  mock(ExamRepository.class);
        ExamService examService  =  new ExamServiceImpl(examRepository); 
        List<Exam> exams = Collections.emptyList();
        when(examRepository.findAll()).thenReturn(exams);

        Exam exam = examService.findExamByName("Matemáticas");

        assertNull(exam);

    } */
    ExamRepository examRepository;
    ExamService examService;

    @BeforeEach
    void setUp(){
        examRepository =  mock(ExamRepository.class);
        examService  =  new ExamServiceImpl(examRepository); 

    }

    @Test
    @DisplayName("Refactorizar implementación de ExamService")
    void Kata4() {

        List<Exam> exams = Arrays.asList(new  Exam(1L, "Matemáticas"), new  Exam(3L, "Lenguaje"), new  Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertTrue(exam.isPresent());
        assertEquals("Matemáticas", exam.get().getName());
        assertEquals(1L, exam.get().getId());
        

    }

    @Test
    void Kata4_a() {

        List<Exam> exams = Collections.emptyList();
        when(examRepository.findAll()).thenReturn(exams);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertFalse(exam.isPresent());

    }
}
