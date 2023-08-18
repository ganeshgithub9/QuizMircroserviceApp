package com.ganesh.quizservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseDTO {
    List<Integer> qsnIds;
    List<String> responses;
}
