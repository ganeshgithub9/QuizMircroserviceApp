package com.ganesh.quizservice.Controllers;


import com.ganesh.quizservice.DTO.*;
import com.ganesh.quizservice.Enums.Subject;
import com.ganesh.quizservice.Feign.QuestionInterface;
import com.ganesh.quizservice.Models.Quiz;
import com.ganesh.quizservice.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    QuizService quizService;

    QuestionInterface questionInterface;

    @Autowired
    QuizController(QuizService qs,QuestionInterface qi){
        quizService=qs;
        questionInterface=qi;
    }
    @PostMapping
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
       //String subL=quizDTO.getSubject().toLowerCase();
        GetQsnIdsDTO getQsnIdsDTO=new GetQsnIdsDTO();getQsnIdsDTO.setNoOfQsns(quizDTO.getNoOfQsns());
        getQsnIdsDTO.setSubject(quizDTO.getSubject());
//       boolean result=questionInterface.isRequiredQsnCountPresentForGivenSubject(getQsnIdsDTO);
        List<Integer> list=questionInterface.getQsnids(getQsnIdsDTO).getBody();
//        if(!result)
//            return new ResponseEntity<>(subL+" questions are not present in database",HttpStatus.PRECONDITION_FAILED);
//        result=checkRequiredQsnCount(quizDTO.getNoOfQsns(),subL);
        if(list==null)
            return new ResponseEntity<>("Sufficient number of questions are not present in database",HttpStatus.PRECONDITION_FAILED);
        boolean result=quizService.createQuiz(quizDTO,list);
        String res=result?"Quiz created":"Quiz not created";
        HttpStatus stat=result?HttpStatus.OK:HttpStatus.CONFLICT;
        return new ResponseEntity<>(res,stat);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizGetDTO> getQuiz(@PathVariable Integer id){
        Quiz quiz=quizService.getQuiz(id);
        if(quiz==null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        List<QuestionWithoutAnswer> qwa=quiz.getQuestionIds().stream().map(question -> QuestionWithoutAnswer.builder().id(question.getId())
//                .statement(question.getStatement()).option1(question.getOption1()).option2(question.getOption2())
//                .option3(question.getOption3()).option4(question.getOption4()).build()).toList();
        List<QuestionWithoutAnswer> qwa=questionInterface.getQsnsByIds(quiz.getQuestionIds()).getBody();
        QuizGetDTO quizGetDTO=QuizGetDTO.builder().id(quiz.getId()).noOfQsns(quiz.getNoOfQsns()).subject(quiz.getSubject())
                .name(quiz.getName()).questionWithoutAnswerList(qwa).build();
        return new ResponseEntity<>(quizGetDTO,HttpStatus.OK);
    }

    @PostMapping("/{id}/score")
    public ResponseEntity<Long> getScore(@RequestBody List<String> list,@PathVariable Integer id){
        Quiz qsn=quizService.findById(id);
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setQsnIds(qsn.getQuestionIds());
        responseDTO.setResponses(list);
        return questionInterface.getScore(responseDTO);
    }



//    private boolean checkRequiredQsnCount(Integer noOfQsns,String subject) {
//        int req=(int)noOfQsns,present=(int)Question.mapToStoreQsnCountPerCategory.get(subject);
//        return req>present?false:true;
//    }
}
