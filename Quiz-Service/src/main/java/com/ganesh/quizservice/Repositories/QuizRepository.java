package com.ganesh.quizservice.Repositories;


import com.ganesh.quizservice.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
