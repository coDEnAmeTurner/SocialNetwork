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

        Query query = s.createQuery(
                "select ur.id, "
                + "ur.roleName, "
                + "u.id, "
                + "u.fullName, "
                + "u.avatar, "
                + "u.username, "
                + "u.dob, "
                + "u.email, "
                + "u.phone, "
                + "d.id, "
                + "d.degreeName, "
                + "ar.id, "
                + "ar.academicRankName, "
                + "a.studentId, "
                + "a.approved\n"
                + "from TypicalUser tu left outer join AcademicRank ar on tu.academicRankId = ar.id\n"
                + "inner join Degree d on tu.degreeId = d.id\n"
                + "inner join Alumnus a on tu.userId = a.typicalUserId\n"
                + "inner join User u on tu.userId = u.id, UserRole ur\n"
                + "where u.userRoleId = ur.id");

        return query.getResultList();
    }

    @Override
    public List<Object[]> getLecturersAndRelatedInfo() {
        Session s = this.factory.getObject().getCurrentSession();

        Query query = s.createQuery(
                "select t.titleName,"
                + "ur.id, "
                + "ur.roleName, "
                + "u.id, "
                + "u.fullName, "
                + "u.avatar, "
                + "u.username, "
                + "u.dob, "
                + "u.email, "
                + "u.phone, "
                + "d.id, "
                + "d.degreeName, "
                + "ar.id, "
                + "ar.academicRankName, "
                + "l.locked, "
                + "l.dispatchingAdminId.userId\n"
                + "from TypicalUser tu left outer join AcademicRank ar on tu.academicRankId = ar.id\n"
                + "inner join Degree d on tu.degreeId = d.id\n"
                + "inner join Lecturer l on tu.userId = l.typicalUserId\n"
                + "inner join User u on tu.userId = u.id, UserRole ur, Title t\n"
                + "where u.userRoleId = ur.id and l.titleId = t.id");

        return query.getResultList();
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

    public List<User> getUsersByUsername(String queryName) {
        Session s = factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(User.class);
        Root r = q.from(User.class);
        q.select(r);
        q.where(b.like(r.get("username"), String.format("%%%s%%", queryName)));
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> countUsersByYear(int startYear, int endYear) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createQuery(
                "select year(user.createdAt), count(user.id)\n"
                + "from User user\n"
                + "where year(user.createdAt) <=: endYear and year(user.createdAt) >=: startYear\n"
                + "group by year(user.createdAt)"
        );

        q.setParameter("startYear", startYear);
        q.setParameter("endYear", endYear);

        return q.getResultList();

    }

    @Override
    public List<Object[]> countUsersByMonth(int year) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createQuery(
                "select year(user.createdAt), month(user.createdAt), count(user.id)\n"
                + "from User user\n"
                + "where year(user.createdAt) =: year\n"
                + "group by year(user.createdAt), month(user.createdAt)"
        );

        q.setParameter("year", year);

        return q.getResultList();
    }

    @Override
    public List<Object[]> countUsersByQuarter(int year) {
        Session s = factory.getObject().getCurrentSession();

        Query q = s.createQuery(
                "select year(user.createdAt), quarter(user.createdAt), count(user.id)\n"
                + "from User user\n"
                + "where year(user.createdAt) =: year\n"
                + "group by year(user.createdAt), quarter(user.createdAt)"
        );

        q.setParameter("year", year);

        return q.getResultList();
    }
}
