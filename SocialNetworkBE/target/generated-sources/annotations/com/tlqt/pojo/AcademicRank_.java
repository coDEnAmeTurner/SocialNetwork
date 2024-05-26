package com.tlqt.pojo;

import com.tlqt.pojo.TypicalUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-26T23:45:00")
@StaticMetamodel(AcademicRank.class)
public class AcademicRank_ { 

    public static volatile SingularAttribute<AcademicRank, String> academicRankName;
    public static volatile SingularAttribute<AcademicRank, Integer> id;
    public static volatile SetAttribute<AcademicRank, TypicalUser> typicalUserSet;

}