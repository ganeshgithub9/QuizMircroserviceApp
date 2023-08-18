package com.ganesh.quizservice.DTO;


import com.ganesh.quizservice.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    Subject subject;
    String statement,option1,option2,option3,option4,answer;
}
