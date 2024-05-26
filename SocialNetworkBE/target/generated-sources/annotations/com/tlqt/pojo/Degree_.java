package com.tlqt.pojo;

import com.tlqt.pojo.TypicalUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:45:00")
@StaticMetamodel(Degree.class)
public class Degree_ { 

    public static volatile SingularAttribute<Degree, String> degreeName;
    public static volatile SingularAttribute<Degree, Integer> id;
    public static volatile SetAttribute<Degree, TypicalUser> typicalUserSet;

}