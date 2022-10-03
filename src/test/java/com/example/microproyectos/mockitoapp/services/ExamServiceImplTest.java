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

import com.example.microproyectos.mockitoapp.models.Exam;
import com.example.microproyectos.mockitoapp.repositories.ExamRepository;

public class ExamServiceImplTest {

	ExamRepository examRepository;
	ExamService examService;
	/*
	 * Modificar el test creado utilizando Mockito (Kata 3)
	 * Modifica el test que permita comprobar el funcionamiento de findExamByName()
	 * para que no dependa de la implementación de ExamRepositoryImpl.java
	 */
	/*
	 * @Test
	 * 
	 * @DisplayName("should test method service using Mocks")
	 * void testKata3() {
	 * ExamRepository examRepository = mock(ExamRepository.class);
	 * ExamService examService = new ExamServiceImpl(examRepository);
	 * List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L,
	 * "Lenguaje"), new Exam(7L, "Música"));
	 * when(examRepository.findAll()).thenReturn(exams);
	 * Exam exam = examService.findExamByName("Matemáticas");
	 * assertNotNull(exam);
	 * assertEquals(1L, exam.getId());
	 * }
	 */

	/*
	 * Refactorizar implementación de ExamService (Kata 4)
	 * Devolvemos en el servicio un Optional< Exam >
	 * Modificamos el test para que verifique comportamiento
	 * Agregamos una nueva prueba que verifique el caso de no encontrar el examen
	 */

	// kata5
	@BeforeEach
	void setUp() {
		examRepository = mock(ExamRepository.class);
		examService = new ExamServiceImpl(examRepository);
	}

	@Test
	@DisplayName("should obtain Math exam when search by name")
	void testKata4_a() {
		List<Exam> exams = Arrays.asList(new Exam(1L, "Matemáticas"), new Exam(3L, "Lenguaje"), new Exam(7L, "Música"));
		when(examRepository.findAll()).thenReturn(exams);

		Optional<Exam> examOptional = examService.findExamByName("Matemáticas");

		assertNotNull(examOptional);
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

		assertNotNull(examOptional);
		assertFalse(examOptional.isPresent());

	}

	/*
	 * Ciclo de vida del test (Kata 5)
	 * Refactorizar la clase test para que el repositorio y el servicio se
	 * instancien en un @BeforeEach y dejar el código más limpio
	 * Generar un nuevo test para verificar el comportamiento cuando se recibe una
	 * respuesta vacía desde el repositorio
	 */

	@Test
	@DisplayName("should don't obtain exam when the list is empty")
	void testKata5() {
		List<Exam> exams = Collections.emptyList();
		when(examRepository.findAll()).thenReturn(exams);

		Optional<Exam> examOptional = examService.findExamByName("Deportes");

		assertNotNull(examOptional);
		assertFalse(examOptional.isPresent());

	}

	/*
	 * Implementar un nuevo repositorio para obtener preguntas (Kata 6)
	 * Se requiere obtener desde otro origen de datos las preguntas de los exámenes
	 * 
	 * Agregar la interfaz QuestionRepository
	 * Implementar un método en ExamService.java que busque las respuestas por
	 * examen
	 * Generar un test que verifique la obtención de preguntas
	 * Generar un test que verifique el comportamiento cuando el examen no tiene
	 * preguntas
	 */

}
