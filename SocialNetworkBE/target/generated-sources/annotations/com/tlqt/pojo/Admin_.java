package com.tlqt.pojo;

import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(Admin.class)
public class Admin_ { 

    public static volatile SetAttribute<Admin, Lecturer> lecturerSet;
    public static volatile SingularAttribute<Admin, User> user;

}