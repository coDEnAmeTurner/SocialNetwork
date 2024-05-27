package com.tlqt.pojo;

import com.tlqt.pojo.SurveyQuestion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(Choice.class)
public class Choice_ { 

    public static volatile SingularAttribute<Choice, SurveyQuestion> surveyQuestionId;
    public static volatile SingularAttribute<Choice, Integer> id;
    public static volatile SingularAttribute<Choice, Integer> voteCount;
    public static volatile SingularAttribute<Choice, String> content;

}