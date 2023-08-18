package com.ganesh.quizservice.DTO;


import com.ganesh.quizservice.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetQsnIdsDTO {
    Subject subject;
    Integer noOfQsns;
}
