package com.tlqt.pojo;

import com.tlqt.pojo.AcademicRank;
import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Degree;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(TypicalUser.class)
public class TypicalUser_ { 

    public static volatile SingularAttribute<TypicalUser, Alumnus> alumnus;
    public static volatile SingularAttribute<TypicalUser, Degree> degreeId;
    public static volatile SingularAttribute<TypicalUser, Lecturer> lecturer;
    public static volatile SingularAttribute<TypicalUser, User> user;
    public static volatile SingularAttribute<TypicalUser, AcademicRank> academicRankId;

}