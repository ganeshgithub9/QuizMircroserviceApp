package com.ganesh.quizservice.Models;

import com.ganesh.quizservice.Enums.Subject;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "quizzes")
public class Quiz {
    // static Map<String,Integer> mapToStoreQuizCountperCategory=new HashMap<>();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    Subject subject;
    Integer noOfQsns;

    @ElementCollection
    List<Integer> questionIds;

    @Setter(AccessLevel.NONE)
    String name;

    public void setName(){
        //  String subCap= StringUtils.capitalize(subject);
//        if(mapToStoreQuizCountperCategory.containsKey(subject))
//            mapToStoreQuizCountperCategory.put(subject,mapToStoreQuizCountperCategory.get(subject)+1);
//        else
//            mapToStoreQuizCountperCategory.put(subject,1);
        name=subject+" Quiz";
    }
}
