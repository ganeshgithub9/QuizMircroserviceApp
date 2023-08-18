package com.ganesh.questionservice.DTO;

import com.ganesh.questionservice.Enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetQsnIdsDTO {
    Subject subject;
    Integer noOfQsns;
}
