package com.ganesh.questionservice.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class QuestionWithoutAnswer {
    Integer id;
    String statement,option1,option2,option3,option4;
}
