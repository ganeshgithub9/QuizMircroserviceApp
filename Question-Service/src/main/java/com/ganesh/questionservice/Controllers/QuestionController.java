package com.ganesh.questionservice.Controllers;


import com.ganesh.questionservice.DTO.GetQsnIdsDTO;
import com.ganesh.questionservice.DTO.QuestionDTO;
import com.ganesh.questionservice.DTO.QuestionWithoutAnswer;
import com.ganesh.questionservice.DTO.ResponseDTO;
import com.ganesh.questionservice.Models.Question;
import com.ganesh.questionservice.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    QuestionService questionService;
    Environment env;

    @Autowired
    QuestionController(QuestionService qs,Environment e){
        questionService=qs;
        env=e;
    }
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionDTO questionDTO){
        boolean result= questionService.createQuestion(questionDTO);
        if(result)
            return new ResponseEntity<>("Question created", HttpStatus.OK);
        return new ResponseEntity<>("Question not created",HttpStatus.CONFLICT);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionWithoutAnswer> getQuestion(@PathVariable Integer id){
        Optional<Question> qu=questionService.getQuestion(id);
        return qu.map((q) -> new ResponseEntity<>(QuestionWithoutAnswer.builder().id(q.getId()).statement(q.getStatement()).option1(q.getOption1()).option2(q.getOption2())
                        .option3(q.getOption3()).option4(q.getOption4()).build(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/getQsnIds")
    public ResponseEntity<List<Integer>> getQsnids(@RequestBody GetQsnIdsDTO getQsnIdsDTO){
        boolean check=isRequiredQsnCountPresentForGivenSubject(getQsnIdsDTO);
        if(!check)
            return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
        return new ResponseEntity<>(questionService.getQsnids(getQsnIdsDTO),HttpStatus.OK);
    }

    @PostMapping("/getQsnsByIds")
    public ResponseEntity<List<QuestionWithoutAnswer>> getQsnsByIds(@RequestBody List<Integer> qsnIds){
        List<Optional<Question>> optionalQsns=questionService.getQsnsByIds(qsnIds);
        List<QuestionWithoutAnswer> qsns=optionalQsns.stream().map(Optional::get).map(q->QuestionWithoutAnswer.builder().id(q.getId())
                .statement(q.getStatement()).option1(q.getOption1()).option2(q.getOption2()).option3(q.getOption3()).option4(q.getOption4()).build()).toList();
        System.out.println(env.getProperty("local.server.port"));
        return new ResponseEntity<>(qsns,HttpStatus.OK);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Long> getScore(@RequestBody ResponseDTO responseDTO){
        return new ResponseEntity<>(questionService.getScore(responseDTO),HttpStatus.OK);
    }

    @PostMapping("/isRequiredQsnCountPresentForGivenSubject")
    public boolean isRequiredQsnCountPresentForGivenSubject(GetQsnIdsDTO getQsnIdsDTO){
        Integer count=questionService.isRequiredQsnCountPresentForGivenSubject(getQsnIdsDTO);
        return (int)count>=(int)getQsnIdsDTO.getNoOfQsns();
    }

//    @GetMapping
//    public Question getQuestionBySubject(@RequestParam Subject subject){
//
//    }

    //@GetMapping("/countMap")
//    public ResponseEntity<List<Integer>> getQsnIdsByTopic(@RequestParam String subject){
//        String subL=subject.toLowerCase();
//         List<Integer> result=Question.mapToStoreQsnIdPerCategory.getOrDefault(subL, null);
//         return new ResponseEntity<>(result,HttpStatus.OK);
//    }
//    public Map<String,Integer> getCountMap(){
//        //return Question.mapToStoreQsnIdPerCategory;
//        return Question.mapToStoreQsnCountPerCategory;
//    }
//
//    @GetMapping("/idMap")
//    public Map<String,List<Integer>> getIdMap(){
//        return Question.mapToStoreQsnIdPerCategory;
//    }
}
