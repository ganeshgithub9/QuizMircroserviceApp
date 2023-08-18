package com.ganesh.quizservice.Feign;

import com.ganesh.quizservice.DTO.GetQsnIdsDTO;
import com.ganesh.quizservice.DTO.QuestionDTO;
import com.ganesh.quizservice.DTO.QuestionWithoutAnswer;
import com.ganesh.quizservice.DTO.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient("QUESTION-SERVICE")
public interface QuestionInterface {

//    @PostMapping("/isRequiredQsnCountPresentForGivenSubject")
//    public boolean isRequiredQsnCountPresentForGivenSubject(GetQsnIdsDTO getQsnIdsDTO);

    @PostMapping("/questions/getQsnIds")
    public ResponseEntity<List<Integer>> getQsnids(@RequestBody GetQsnIdsDTO getQsnIdsDTO);

    @PostMapping("/questions/getQsnsByIds")
    public ResponseEntity<List<QuestionWithoutAnswer>> getQsnsByIds(@RequestBody List<Integer> qsnIds);

    @PostMapping("/questions/getScore")
    public ResponseEntity<Long> getScore(@RequestBody ResponseDTO responseDTO);

}
