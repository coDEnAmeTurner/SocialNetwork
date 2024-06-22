/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories.impl;

import com.tlqt.pojo.AcademicRank;
import com.tlqt.pojo.Admin;
import com.tlqt.pojo.Alumnus;
import com.tlqt.pojo.Comment;
import com.tlqt.pojo.Degree;
import com.tlqt.pojo.Lecturer;
import com.tlqt.pojo.Post;
import com.tlqt.pojo.SurveyQuestion;
import com.tlqt.pojo.Title;
import com.tlqt.pojo.TypicalUser;
import com.tlqt.pojo.User;
import com.tlqt.pojo.UserRole;
import com.tlqt.repositories.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findAll");

        return q.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByEmail");
        q.setParameter("email", email);

        return (User) q.getSingleResult();
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        query.select(root);

        String usernameFormat = String.format("%s", username);
        Predicate p1 = builder.equal(root.get("username").as(String.class), usernameFormat);
        String passwordFormat = String.format("%s", password);
        Predicate p2 = builder.equal(root.get("password").as(String.class), passwordFormat);

        query = query.where(builder.and(p1, p2));

        return (User) s.createQuery(query).getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        User foundByUsername = this.getUserByUsername(username);

        return this.passEncoder.matches(password, foundByUsername.getPassword());
    }

    @Override
    public List<Object[]> getAlumniAndRelatedInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<User> uRoot = query.from(User.class);
        Root<TypicalUser> tuRoot = query.from(TypicalUser.class);
        Root<Alumnus> aRoot = query.from(Alumnus.class);
        Root<UserRole> urRoot = query.from(UserRole.class);
        Root<Degree> dRoot = query.from(Degree.class);
        Root<AcademicRank> arRoot = query.from(AcademicRank.class);

        Predicate p1 = builder.equal(uRoot.get("id"), tuRoot.get("user"));
        Predicate p2 = builder.equal(tuRoot.get("userId"), aRoot.get("typicalUser"));
        Predicate p4 = builder.equal(urRoot.get("id"), uRoot.get("userRoleId"));
        Predicate p7 = builder.equal(dRoot.get("id"), tuRoot.get("degreeId"));
        Predicate p8 = builder.equal(arRoot.get("id"), tuRoot.get("academicRankId"));

        query.where(builder.and(
                p1,
                p2,
                p4,
                p7,
                p8
        ));

        query.multiselect(
                uRoot.get("id"),
                uRoot.get("fullName"),
                uRoot.get("avatar"),
                uRoot.get("username"),
                urRoot.get("id"),
                urRoot.get("roleName"),
                uRoot.get("dob"),
                uRoot.get("email"),
                uRoot.get("phone"),
                dRoot.get("id"),
                dRoot.get("degreeName"),
                arRoot.get("id"),
                arRoot.get("academicRankName"),
                aRoot.get("studentId")
        );

        Query qquery = s.createQuery(query);
        List<Object[]> rs = qquery.getResultList();

        return rs;
    }

    @Override
    public List<Object[]> getLecturersAndRelatedInfo() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();

        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);

        Root<User> uRoot = query.from(User.class);
        Root<TypicalUser> tuRoot = query.from(TypicalUser.class);
        Root<Lecturer> lRoot = query.from(Lecturer.class);
        Root<UserRole> urRoot = query.from(UserRole.class);
        Root<Degree> dRoot = query.from(Degree.class);
        Root<AcademicRank> arRoot = query.from(AcademicRank.class);
        Root<Title> tRoot = query.from(Title.class);
        Root<Admin> adRoot = query.from(Admin.class);

        Predicate p1 = builder.equal(uRoot.get("id"), tuRoot.get("user"));
        Predicate p2 = builder.equal(tuRoot.get("userId"), lRoot.get("typicalUser"));
        Predicate p4 = builder.equal(urRoot.get("id"), uRoot.get("userRoleId"));
        Predicate p7 = builder.equal(dRoot.get("id"), tuRoot.get("degreeId"));
        Predicate p8 = builder.equal(arRoot.get("id"), tuRoot.get("academicRankId"));
        Predicate p9 = builder.equal(tRoot.get("id"), lRoot.get("titleId"));
        Predicate p10 = builder.equal(adRoot.get("userId"), lRoot.get("dispatchingAdminId"));

        query.where(builder.and(
                p1,
                p2,
                p4,
                p7,
                p8,
                p9
        ));

        query.multiselect(
                uRoot.get("id"),
                uRoot.get("fullName"),
                uRoot.get("avatar"),
                uRoot.get("username"),
                urRoot.get("id"),
                urRoot.get("roleName"),
                uRoot.get("dob"),
                uRoot.get("email"),
                uRoot.get("phone"),
                dRoot.get("id"),
                dRoot.get("degreeName"),
                arRoot.get("id"),
                arRoot.get("academicRankName"),
                lRoot.get("locked"),
                adRoot.get("userId"),
                tRoot.get("titleName")
        );

        Query qquery = s.createQuery(query);
        List<Object[]> rs = qquery.getResultList();

        return rs;
    }

    @Override
    public User getAuthorByPostId(int postId) {
        Session s = factory.getObject().getCurrentSession();

        Query query = s.createNamedQuery("Post.findById");
        query.setParameter("id", postId);
        Post p = (Post) query.getSingleResult();

        return p.getUserId();
    }

    @Override
    public User getUserById(int id) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("User.findById");
        q.setParameter("id", id);

        return (User) q.getSingleResult();
    }

    @Override
    public User getUserByCommentId(int commentId) {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findById");
        q.setParameter("id", commentId);

        Comment c = (Comment) q.getSingleResult();

        return c.getUserId();

    }

    @Override
    public void update(User user) {
        Session s = factory.getObject().getCurrentSession();

        s.update(user);
    }

}
