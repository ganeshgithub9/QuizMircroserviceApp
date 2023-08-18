package com.ganesh.questionservice.Repositories;
import com.ganesh.questionservice.Enums.Subject;
import com.ganesh.questionservice.InterfacesToQueries.QuestionId;
import com.ganesh.questionservice.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Query("select count(q.id) from Question q where q.subject=:subject")
    Integer findCountBySubject(Subject subject);

    List<QuestionId> findAllBySubject(Subject subject);

    @Query("select q.answer from Question q where q.id=:id")
    String findAnswerById(Integer id);

}
