package com.ganesh.questionservice.Services;

import com.ganesh.questionservice.DTO.GetQsnIdsDTO;
import com.ganesh.questionservice.DTO.QuestionDTO;
import com.ganesh.questionservice.DTO.QuestionWithoutAnswer;
import com.ganesh.questionservice.DTO.ResponseDTO;
import com.ganesh.questionservice.Enums.Subject;
import com.ganesh.questionservice.InterfacesToQueries.QuestionId;
import com.ganesh.questionservice.Models.Question;
import com.ganesh.questionservice.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class QuestionService {
    QuestionRepository questionRepository;

    @Autowired
    QuestionService(QuestionRepository qr){
        questionRepository=qr;
    }

    public boolean createQuestion(QuestionDTO questionDTO){
        boolean result;
        try{
            Question q=new Question();
            q.setSubject(questionDTO.getSubject());
            q.setStatement(questionDTO.getStatement());
            q.setOption1(questionDTO.getOption1());
            q.setOption2(questionDTO.getOption2());
            q.setOption3(questionDTO.getOption3());
            q.setOption4(questionDTO.getOption4());
            q.setAnswer(questionDTO.getAnswer());
            questionRepository.save(q);
            //q.updateMaps();
            result=true;
        }
        catch (Exception e){
            result=false;
        }
        return result;
    }

    public Optional<Question> getQuestion(Integer id) {
        return questionRepository.findById(id);
    }

    public List<Integer> getQsnids(GetQsnIdsDTO getQsnIdsDTO){
        List<QuestionId> subjectQsnIds=questionRepository.findAllBySubject(getQsnIdsDTO.getSubject());
        List<Integer> answer=new ArrayList<>();
        int size=0,subjectQsnsSize=subjectQsnIds.size();
        Set<Integer> qsns=new HashSet<>();
        Random random=new Random();
        while(size<getQsnIdsDTO.getNoOfQsns()){
            Integer randomIndex=random.nextInt(subjectQsnsSize);
            if(!qsns.contains(randomIndex)){
                answer.add(subjectQsnIds.get(randomIndex).getId());
                qsns.add(randomIndex);
                size++;
            }
        }
        return answer;
    }

    public Integer isRequiredQsnCountPresentForGivenSubject(GetQsnIdsDTO getQsnIdsDTO){
        Integer count=questionRepository.findCountBySubject(getQsnIdsDTO.getSubject());
        return count;
    }

    public List<Optional<Question>> getQsnsByIds(List<Integer> qsnIds) {
        return qsnIds.stream().map(id->questionRepository.findById(id)).toList();
    }

    public Long getScore(ResponseDTO responseDTO) {
        List<String> answers=responseDTO.getQsnIds().stream().map(id-> questionRepository.findAnswerById(id)).toList();
        long count= IntStream.range(0,answers.size()).filter(i->answers.get(i).equals(responseDTO.getResponses().get(i))).count();
        return count;
    }
}
