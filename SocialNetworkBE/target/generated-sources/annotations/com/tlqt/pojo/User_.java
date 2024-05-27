package com.tlqt.pojo;

import com.tlqt.pojo.ActionComment;
import com.tlqt.pojo.ActionPost;
import com.tlqt.pojo.Admin;
import com.tlqt.pojo.Comment;
import com.tlqt.pojo.Invitation;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.pojo.UserRole;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-05-27T22:33:00")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SetAttribute<User, ActionPost> actionPostSet;
    public static volatile SingularAttribute<User, String> fullName;
    public static volatile SingularAttribute<User, Admin> admin;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SetAttribute<User, Post> postSet;
    public static volatile SingularAttribute<User, ActionComment> actionComment;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SetAttribute<User, Comment> commentSet;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, Date> dob;
    public static volatile SetAttribute<User, Invitation> invitationSet;
    public static volatile SingularAttribute<User, TypicalUser> typicalUser;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, UserRole> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}