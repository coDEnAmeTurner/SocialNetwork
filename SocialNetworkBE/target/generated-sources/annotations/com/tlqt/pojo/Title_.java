package com.tlqt.pojo;

import com.tlqt.pojo.Lecturer;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-23T01:53:53")
@StaticMetamodel(Title.class)
public class Title_ { 

    public static volatile SingularAttribute<Title, String> titleName;
    public static volatile SetAttribute<Title, Lecturer> lecturerSet;
    public static volatile SingularAttribute<Title, Integer> id;

}