package com.ganesh.questionservice.DTO;

import com.ganesh.questionservice.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    Subject subject;
    String statement,option1,option2,option3,option4,answer;
}
