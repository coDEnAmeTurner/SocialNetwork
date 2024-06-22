package com.tlqt.pojo;

import com.tlqt.pojo.Post;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(Invitation.class)
public class Invitation_ { 

    public static volatile SingularAttribute<Invitation, Date> dateTime;
    public static volatile SingularAttribute<Invitation, Post> post;
    public static volatile SingularAttribute<Invitation, String> location;
    public static volatile SingularAttribute<Invitation, Integer> postId;

}