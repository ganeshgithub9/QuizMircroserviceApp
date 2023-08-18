package com.ganesh.questionservice.Models;

import com.ganesh.questionservice.Enums.Subject;
import com.ganesh.questionservice.Enums.Subject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "questions")
public class Question {
    //public static Map<String, List<Integer>> mapToStoreQsnIdPerCategory=new HashMap<>();
    //public static Map<String, Integer> mapToStoreQsnCountPerCategory=new HashMap<>();
    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    Subject subject;
    String statement,option1,option2,option3,option4,answer;

//    public void updateMaps(){
//        if(mapToStoreQsnIdPerCategory.containsKey(subject)) {
//            mapToStoreQsnIdPerCategory.get(subject).add(id);
//            mapToStoreQsnCountPerCategory.put(subject,mapToStoreQsnCountPerCategory.get(subject)+1);
//        }
//        else {
//            List<Integer> ar=new ArrayList<>();ar.add(id);
//            mapToStoreQsnIdPerCategory.put(subject,ar);
//            mapToStoreQsnCountPerCategory.put(subject,1);
//        }
  //  }
}
