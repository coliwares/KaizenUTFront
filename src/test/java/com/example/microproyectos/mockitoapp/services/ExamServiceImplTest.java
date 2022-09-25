package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.ExamRepositoryImpl;
import com.example.microproyectos.mockitoapp.repositories.QuestionRepository;

public class ExamServiceImplTest {
    /*
     * @Test
     * 
     * @DisplayName("should test method service using repository Implement")
     * 
     * @Disabled
     * void testKata2_a() {
     * ExamRepository examRepository = new ExamRepositoryImpl();
     * ExamService examService = new ExamServiceImpl(examRepository);
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNotNull(exam);
     * assertEquals(1L, exam.getId());
     * 
     * }
     * 
     * @Test
     * 
     * @DisplayName("should test method service using Mocks")
     * 
     * @Disabled
     * void testKata3() {
     * ExamRepository examRepository = mock(ExamRepository.class);
     * ExamService examService = new ExamServiceImpl(examRepository);
     * List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"),new Exam(3L,
     * "Lenguaje"),new Exam(7L, "Música"));
     * when(examRepository.findAll()).thenReturn(exams);
     * 
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNotNull(exam);
     * assertEquals(1L, exam.getId());
     * 
     * }
     */
    ExamRepository examRepository;
    QuestionRepository questionRepository;
    ExamService examService;

    @BeforeEach
    void setUp() {
        examRepository = mock(ExamRepository.class);
        questionRepository = mock(QuestionRepository.class);
        examService = new ExamServiceImpl(examRepository, questionRepository);
    }

    @Test
    @DisplayName("should obtain Math exam when search by name")
    void testKata4_a() {
        List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);

        Optional<Exam> examOptional = examService.findExamByName("Matemáticas");

        assertTrue(examOptional.isPresent());
        assertEquals(1L, examOptional.get().getId());
        assertEquals("Matemáticas", examOptional.get().getName());

    }

    @Test
    @DisplayName("should don't obtain exam when search by name not in list")
    void testKata4_b() {
        List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);

        Optional<Exam> examOptional = examService.findExamByName("Deportes");

        assertFalse(examOptional.isPresent());

    }

    @Test
    @DisplayName("should don't obtain exam when the list is empty")
    void testKata5() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        Optional<Exam> examOptional = examService.findExamByName("Deportes");

        assertFalse(examOptional.isPresent());

    }

    @Test
    @DisplayName("should obtain exam plus questions by exam name")
    void testKata6_a() {
        List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);
        List<String> questions = Arrays.asList("prugunta 1", "2 + 2 =", "integral derivada....");
        when(questionRepository.findQuestionsbyId(1L)).thenReturn(questions);

        Exam exam = examService.FindExamWithQuestionsByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(3, exam.getQuestions().size());
        assertFalse(exam.getQuestions().isEmpty());
        assertTrue(exam.getQuestions().contains("2 + 2 ="));
    }

    @Test
    @DisplayName("should obtain exam but dont have questions by exam name")
    void testKata6_b() {
        List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
        when(examRepository.findAll()).thenReturn(exams);
        List<String> questions = Collections.emptyList();
        when(questionRepository.findQuestionsbyId(1L)).thenReturn(questions);

        Exam exam = examService.FindExamWithQuestionsByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(0, exam.getQuestions().size());
        assertTrue(exam.getQuestions().isEmpty());
    }

}
