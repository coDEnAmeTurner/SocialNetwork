package com.tlqt.pojo;

import com.tlqt.pojo.ActionPost;
import com.tlqt.pojo.Comment;
import com.tlqt.pojo.ContentType;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.PostImage;
import com.tlqt.pojo.Survey;
import com.tlqt.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SetAttribute<Post, ActionPost> actionPostSet;
    public static volatile SingularAttribute<Post, Invitation> invitation;
    public static volatile SingularAttribute<Post, ContentType> contentTypeId;
    public static volatile SingularAttribute<Post, String> title;
    public static volatile SingularAttribute<Post, User> userId;
    public static volatile SingularAttribute<Post, Boolean> unlocked;
    public static volatile SingularAttribute<Post, String> content;
    public static volatile SingularAttribute<Post, Date> createdAt;
    public static volatile SetAttribute<Post, Comment> commentSet;
    public static volatile SetAttribute<Post, PostImage> postImageSet;
    public static volatile SingularAttribute<Post, Survey> survey;
    public static volatile SingularAttribute<Post, Integer> id;
    public static volatile SingularAttribute<Post, Date> updatedAt;

}