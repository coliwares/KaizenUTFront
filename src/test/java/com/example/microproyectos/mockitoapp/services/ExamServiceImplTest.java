package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.microproyectos.mockitoapp.data.Data;
import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.QuestionRepository;

public class ExamServiceImplTest {

    /*
     * @Test
     * 
     * @DisplayName("Crear un test utilizando la implementación del repositorio")
     * void Kata2() {
     * 
     * ExamRepository examRepository = new ExamRepositoryImpl();
     * ExamService examService = new ExamServiceImpl(examRepository);
     * 
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNotNull(exam);
     * assertEquals("Matemáticas", exam.getName());
     * 
     * 
     * }
     * 
     * @Test
     * void Kata2_a() {
     * 
     * ExamRepository examRepository = new ExamRepositoryImpl();
     * ExamService examService = new ExamServiceImpl(examRepository);
     * 
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNull(exam);
     * 
     * }
     */

    /*
     * @Test
     * 
     * @DisplayName("Modificar el test creado utilizando Mockito")
     * void Kata2() {
     * 
     * ExamRepository examRepository = mock(ExamRepository.class);
     * ExamService examService = new ExamServiceImpl(examRepository);
     * List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L,
     * "Lenguaje"), new Exam(7L, "Música"));
     * when(examRepository.findAll()).thenReturn(exams);
     * 
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNotNull(exam);
     * assertEquals("Matemáticas", exam.getName());
     * assertEquals(1L, exam.getId());
     * 
     * 
     * }
     * 
     * @Test
     * void Kata2_a() {
     * 
     * ExamRepository examRepository = mock(ExamRepository.class);
     * ExamService examService = new ExamServiceImpl(examRepository);
     * List<Exam> exams = Collections.emptyList();
     * when(examRepository.findAll()).thenReturn(exams);
     * 
     * Exam exam = examService.findExamByName("Matemáticas");
     * 
     * assertNull(exam);
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
    @DisplayName("Refactorizar implementación de ExamService")
    void Kata4() {

        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertTrue(exam.isPresent());
        assertEquals("Matemáticas", exam.get().getName());
        assertEquals(1L, exam.get().getId());

    }

    @Test
    void Kata4_a() {

        when(examRepository.findAll()).thenReturn(Data.EXAMS_EMPTY);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertFalse(exam.isPresent());

    }

    @Test
    @DisplayName("Obtener examen con preguntas por nombre")
    void Kata6() {

        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        when(questionRepository.findQuestionsbyId(anyLong())).thenReturn(Data.QUESTIONS);

        Exam exam = examService.FindExamWithQuestionsByName("Lenguaje");

        assertNotNull(exam);
        assertTrue(exam.getQuestions().size() > 1);
        assertFalse(exam.getQuestions().isEmpty());
        assertArrayEquals(Data.QUESTIONS.toArray(), exam.getQuestions().toArray());
        assertEquals("Lenguaje", exam.getName());
        assertEquals(3L, exam.getId());

    }

    @Test
    @DisplayName("Given find a exam when exam name does not existe in the list then return a null exam response")
    void Kata6_b(){
        //Given
        
        //when
        when(examRepository.findAll()).thenReturn(Data.EXAMS_EMPTY);
        Exam exam = examService.FindExamWithQuestionsByName("ciencias");

        //then
        assertNull(exam);

    }

    /* Refactorizar tests utilizando Datos de prueba estáticos (Kata 7)
Crear una clase que mantenga las constantes de los datos de prueba
Refactorizar los test para que tomen la data estática
Crear test que valide la obtención de un examen con preguntas cuando la lista de exámenes este vacía */

    @Test
    @DisplayName("Given find a exam when dont have a list of questions then return a exam without questions")
    void kata7(){
        //Arrange
        when(examRepository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsbyId(anyLong())).thenReturn(Data.QUESTIONS_EMPTY);

        //ACT
        Exam exam = examService.FindExamWithQuestionsByName("Matemáticas");

        //Assert
        assertNotNull(exam);
        assertEquals(0, exam.getQuestions().size());
        assertTrue(exam.getQuestions().isEmpty());
        assertEquals(1L, exam.getId());
        

    }
}
