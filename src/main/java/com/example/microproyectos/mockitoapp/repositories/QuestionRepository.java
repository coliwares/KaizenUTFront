package com.example.microproyectos.mockitoapp.repositories;

import java.util.List;

public interface QuestionRepository {
    List<String> findQuestionsbyId(Long id);
}
