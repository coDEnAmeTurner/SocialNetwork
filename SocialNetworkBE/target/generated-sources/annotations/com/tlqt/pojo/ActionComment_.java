package com.tlqt.pojo;

import com.tlqt.pojo.Comment;
import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(ActionComment.class)
public class ActionComment_ { 

    public static volatile SingularAttribute<ActionComment, Integer> count;
    public static volatile SingularAttribute<ActionComment, Integer> actionId;
    public static volatile SingularAttribute<ActionComment, Comment> commentId;
    public static volatile SingularAttribute<ActionComment, Integer> id;
    public static volatile SingularAttribute<ActionComment, User> userId;

}