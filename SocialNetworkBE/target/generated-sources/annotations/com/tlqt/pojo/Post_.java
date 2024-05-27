package com.tlqt.pojo;

import com.tlqt.pojo.ActionPost;
import com.tlqt.pojo.Comment;
import com.tlqt.pojo.ContentType;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.pojo.TypicalPost;
import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SetAttribute<Post, ActionPost> actionPostSet;
    public static volatile SetAttribute<Post, Comment> commentSet;
    public static volatile SingularAttribute<Post, Invitation> invitation;
    public static volatile SetAttribute<Post, SurveyQuestion> surveyQuestionSet;
    public static volatile SingularAttribute<Post, ContentType> contentTypeId;
    public static volatile SingularAttribute<Post, TypicalPost> typicalPost;
    public static volatile SingularAttribute<Post, Integer> id;
    public static volatile SingularAttribute<Post, String> title;
    public static volatile SingularAttribute<Post, User> userId;
    public static volatile SingularAttribute<Post, Boolean> unlocked;

}