/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.repositories;

import com.tlqt.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UserRepository {

    List<User> getUsers();
    
    List<String> getAllEmails();
    
    List<Object[]> getAllEmailsAndRelatedInfo();

    User getUserByUsername(String username);

    User getUserByUsernameAndPassword(String username, String password);

    User getUserByEmail(String email);

    void addUser(User user);

    boolean authUser(String username, String password);

    List<Object[]> getAlumniAndRelatedInfo();

    List<Object[]> getLecturersAndRelatedInfo();

    User getAuthorByPostId(int postId);

    User getUserById(int id);

    User getUserByCommentId(int commentId);

    void update(User user);

    public List<User> getUsersByUsername(String queryName);

    List<Object[]> countUsersByYear(int startYear, int endYear);

    List<Object[]> countUsersByMonth(int year);

    List<Object[]> countUsersByQuarter(int year);
}
