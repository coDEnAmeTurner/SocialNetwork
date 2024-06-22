package com.tlqt.pojo;

import com.tlqt.pojo.Post;
import com.tlqt.pojo.SurveyQuestion;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(Survey.class)
public class Survey_ { 

    public static volatile SingularAttribute<Survey, Post> post;
    public static volatile SetAttribute<Survey, SurveyQuestion> surveyQuestionSet;
    public static volatile SingularAttribute<Survey, Integer> postId;

}