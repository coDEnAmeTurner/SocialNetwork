package com.tlqt.pojo;

import com.tlqt.pojo.Action;
import com.tlqt.pojo.ActionPostPK;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(ActionPost.class)
public class ActionPost_ { 

    public static volatile SingularAttribute<ActionPost, Post> post;
    public static volatile SingularAttribute<ActionPost, Integer> counts;
    public static volatile SingularAttribute<ActionPost, Action> actionId;
    public static volatile SingularAttribute<ActionPost, User> user;
    public static volatile SingularAttribute<ActionPost, ActionPostPK> actionPostPK;

}