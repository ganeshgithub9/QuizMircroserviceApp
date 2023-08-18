package com.ganesh.quizservice.DTO;


import com.ganesh.quizservice.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizDTO {
    Subject subject;
    Integer noOfQsns;
}
