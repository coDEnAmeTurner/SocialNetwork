/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services;

import com.tlqt.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService {

    List<User> getUsers();

    List<String> getAllEmails();

    List<Object[]> getAllEmailsAndRelatedInfo();

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByEmail(String email);

    void addUser(User user);

    boolean authUser(String username, String password);

    public List<Object[]> getAlumniAndRelatedInfo();

    public List<Object[]> getLecturersAndRelatedInfo();

    User getAuthorByPostId(int postId);

    User getUserById(int id);

    User getUserByCommentId(int commentId);

    void update(User user, Boolean newPass);

    public List<User> getUsersByUsername(String queryName);

    List<Object[]> countUsersByYear(int startYear, int endYear);

    List<Object[]> countUsersByMonth(int year);

    List<Object[]> countUsersByQuarter(int year);
}
