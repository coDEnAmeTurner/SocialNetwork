package com.tlqt.pojo;

import com.tlqt.pojo.SurveyQuestion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(Choice.class)
public class Choice_ { 

    public static volatile SingularAttribute<Choice, SurveyQuestion> surveyQuestionId;
    public static volatile SingularAttribute<Choice, Integer> id;
    public static volatile SingularAttribute<Choice, Integer> voteCount;
    public static volatile SingularAttribute<Choice, String> content;

}