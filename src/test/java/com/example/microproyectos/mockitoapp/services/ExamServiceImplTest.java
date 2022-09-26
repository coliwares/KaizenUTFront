package com.example.microproyectos.mockitoapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import com.example.microproyectos.mockitoapp.data.Data;
import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;
import com.example.microproyectos.mockitoapp.repositories.QuestionRepository;

@ExtendWith(MockitoExtension.class)
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
    @Mock
    ExamRepository examRepository;
    @Mock
    QuestionRepository questionRepository;
    @InjectMocks
    ExamServiceImpl examService;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this);
        /*
         * examRepository = mock(ExamRepository.class);
         * questionRepository = mock(QuestionRepository.class);
         * examService = new ExamServiceImpl(examRepository, questionRepository);
         */ }

    @Test
    @DisplayName("should obtain Math exam when search by name")
    void testKata4_a() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        Optional<Exam> examOptional = examService.findExamByName("Matemáticas");

        assertTrue(examOptional.isPresent());
        assertEquals(1L, examOptional.get().getId());
        assertEquals("Matemáticas", examOptional.get().getName());

    }

    @Test
    @DisplayName("should don't obtain exam when search by name not in list")
    void testKata4_b() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

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
        when(examRepository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsbyId(1L)).thenReturn(Data.QUESTIONS);

        Exam exam = examService.findExamWithQuestionsByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(3, exam.getQuestions().size());
        assertFalse(exam.getQuestions().isEmpty());
        assertTrue(exam.getQuestions().contains("2 + 2 ="));
    }

    @Test
    @DisplayName("should obtain exam but dont have questions by exam name")
    void testKata6_b() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);
        List<String> questions = Collections.emptyList();
        when(questionRepository.findQuestionsbyId(anyLong())).thenReturn(questions);

        Exam exam = examService.findExamWithQuestionsByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(0, exam.getQuestions().size());
        assertTrue(exam.getQuestions().isEmpty());
    }

    @Test
    @DisplayName("should obtain exam but dont have a list of exams")
    void testKata7() {
        when(examRepository.findAll()).thenReturn(Collections.emptyList());

        Exam exam = examService.findExamWithQuestionsByName("Matemáticas");

        assertNull(exam);
    }

    @Test
    @DisplayName("should obtain exam by exam name verifing method call")
    void testKata8_a() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsbyId(1L)).thenReturn(Data.QUESTIONS);

        Exam exam = examService.findExamWithQuestionsByName("Matemáticas");

        assertNotNull(exam);
        assertEquals("Matemáticas", exam.getName());
        assertEquals(3, exam.getQuestions().size());
        assertFalse(exam.getQuestions().isEmpty());
        assertTrue(exam.getQuestions().contains("2 + 2 ="));
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsbyId(1L);
    }

    @Test
    @DisplayName("should obtain exam but dont have questions by exam name verifing method call")
    void testKata8_b() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);

        Exam exam = examService.findExamWithQuestionsByName("deportes");

        assertNull(exam);
        verify(examRepository, atLeastOnce()).findAll();
        verify(questionRepository, never()).findQuestionsbyId(anyLong());
    }

    @Test
    @DisplayName("should save a exam with question and verify save exam method ")
    void testKata10() {
        Exam newExam = Data.EXAM_TO_SAVE;
        newExam.setQuestions(Data.QUESTIONS);
        when(examRepository.save(any(Exam.class))).thenReturn(Data.EXAM_TO_SAVE);
        Exam exam = examService.saveExam(Data.EXAM_TO_SAVE);

        assertNotNull(exam);
//por kata 11        assertEquals(1L, exam.getId());
        assertEquals("Matemáticas", exam.getName());
        verify(examRepository).save(any(Exam.class));
        verify(questionRepository).save(anyList());

    }

    @Test
    @DisplayName("should save a exam with question and verify save exam method implements Answer")
    void testKata11() {
        Exam newExam = Data.EXAM_TO_SAVE;
        newExam.setQuestions(Data.QUESTIONS);

        when(examRepository.save(any(Exam.class))).then(new Answer<Exam>() {
            Long sequence = 10L;
            @Override
            public Exam answer(InvocationOnMock invocation) throws Throwable {
                Exam exam = invocation.getArgument(0);
                exam.setId(sequence++);
                return exam;
            }
            
        });
        Exam exam = examService.saveExam(Data.EXAM_TO_SAVE);

        assertNotNull(exam);
        assertEquals(10L, exam.getId());
        assertEquals("Matemáticas", exam.getName());
        verify(examRepository).save(any(Exam.class));
        verify(questionRepository).save(anyList());

    }

    @Test
    @DisplayName("should throws a exception")
    void testKata12_a() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS);
        when(questionRepository.findQuestionsbyId(anyLong())).thenThrow(IllegalArgumentException.class);

        Exception exception =  assertThrows(IllegalArgumentException.class, () -> {
            examService.findExamWithQuestionsByName("Matemáticas");
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());

    }

    @Test
    @DisplayName("should throws a exception - Correctly")
    void testKata12_b() {
        when(examRepository.findAll()).thenReturn(Data.EXAMS_ID_NULL);
        when(questionRepository.findQuestionsbyId(isNull())).thenThrow(IllegalArgumentException.class);

        Exception exception =  assertThrows(IllegalArgumentException.class, () -> {
            examService.findExamWithQuestionsByName("Matemáticas");
        });

        assertEquals(IllegalArgumentException.class, exception.getClass());
        verify(examRepository).findAll();
        verify(questionRepository).findQuestionsbyId(isNull());
        
    }


}
