package com.tlqt.pojo;

import com.tlqt.pojo.Post;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(ContentType.class)
public class ContentType_ { 

    public static volatile SingularAttribute<ContentType, String> name;
    public static volatile SingularAttribute<ContentType, Integer> id;
    public static volatile SetAttribute<ContentType, Post> postSet;

}