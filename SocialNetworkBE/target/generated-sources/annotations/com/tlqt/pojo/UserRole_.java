package com.tlqt.pojo;

import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:45:00")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile SingularAttribute<UserRole, String> roleName;
    public static volatile SingularAttribute<UserRole, Integer> id;
    public static volatile SetAttribute<UserRole, User> userSet;

}