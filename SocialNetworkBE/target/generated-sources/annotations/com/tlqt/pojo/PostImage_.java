package com.tlqt.pojo;

import com.tlqt.pojo.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(PostImage.class)
public class PostImage_ { 

    public static volatile SingularAttribute<PostImage, String> image;
    public static volatile SingularAttribute<PostImage, Integer> id;
    public static volatile SingularAttribute<PostImage, Post> postId;

}