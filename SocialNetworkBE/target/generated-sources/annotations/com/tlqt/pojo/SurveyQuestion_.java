package com.tlqt.pojo;

import com.tlqt.pojo.Choice;
import com.tlqt.pojo.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:45:00")
@StaticMetamodel(SurveyQuestion.class)
public class SurveyQuestion_ { 

    public static volatile SetAttribute<SurveyQuestion, Choice> choiceSet;
    public static volatile SingularAttribute<SurveyQuestion, Integer> id;
    public static volatile SingularAttribute<SurveyQuestion, Post> postId;
    public static volatile SingularAttribute<SurveyQuestion, String> content;

}