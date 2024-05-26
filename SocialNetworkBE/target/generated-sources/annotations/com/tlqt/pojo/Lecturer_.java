package com.tlqt.pojo;

import com.tlqt.pojo.Admin;
import com.tlqt.pojo.Title;
import com.tlqt.pojo.TypicalUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:45:00")
@StaticMetamodel(Lecturer.class)
public class Lecturer_ { 

    public static volatile SingularAttribute<Lecturer, Admin> dispatchingAdmin;
    public static volatile SingularAttribute<Lecturer, Title> titleId;
    public static volatile SingularAttribute<Lecturer, TypicalUser> typicalUser;
    public static volatile SingularAttribute<Lecturer, Boolean> locked;

}