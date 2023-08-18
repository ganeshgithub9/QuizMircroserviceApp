package com.ganesh.quizservice.Services;


import com.ganesh.quizservice.DTO.QuizDTO;
import com.ganesh.quizservice.Models.Quiz;
import com.ganesh.quizservice.Repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    QuizRepository quizRepository;
    @Autowired
    QuizService(QuizRepository qr){
        quizRepository=qr;
    }
    public boolean createQuiz(QuizDTO quizDTO,List<Integer> list){
        try {
            Quiz quiz = new Quiz();
            quiz.setSubject(quizDTO.getSubject());
            quiz.setNoOfQsns(quizDTO.getNoOfQsns());
            quiz.setName();
            //List<Question> que=findQuestionsRandomly(quizDTO.getNoOfQsns(),quizDTO.getSubject());
            //quiz.setQuestions(que);
            quiz.setQuestionIds(list);
            quizRepository.save(quiz);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

//    public List<Question> findQuestionsRandomly(Integer qsnCount, Subject subject){
//        List<Question> subjectQsns=questionRepository.findAllBySubject(subject);
//        List<Question> answer=new ArrayList<>();
//        int size=0,subjectQsnsSize=subjectQsns.size();
//        Set<Integer> qsns=new HashSet<>();
//        Random random=new Random();
//        while(size<qsnCount){
//            Integer randomIndex=random.nextInt(subjectQsnsSize);
//            if(!qsns.contains(randomIndex)){
//                answer.add(subjectQsns.get(randomIndex));
//                qsns.add(randomIndex);
//                size++;
//            }
//        }
//        return answer;
//    }

    public Quiz getQuiz(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }

    public Quiz findById(Integer id) {
        return quizRepository.findById(id).orElse(null);
    }
//    public long getScore(List<String> list,Integer id) {
//        Quiz q=quizRepository.findById(id).map(quiz -> quiz).orElseGet(null);
//        if(q==null)
//            return 0;
//        long count=0;int i;
//        List<Question> li=q.getQuestions();
//        for(i=0;i<q.getNoOfQsns();i++){
//            if(li.get(i).getAnswer().equals(list.get(i)))
//                count++;
//        }
//        return count;
//    }
}
