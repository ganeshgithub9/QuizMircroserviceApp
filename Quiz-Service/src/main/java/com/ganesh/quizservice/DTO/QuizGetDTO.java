package com.ganesh.quizservice.DTO;


import com.ganesh.quizservice.Enums.Subject;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class QuizGetDTO {
    Integer id,noOfQsns;
    Subject subject;String name;
    List<QuestionWithoutAnswer> questionWithoutAnswerList;
}
