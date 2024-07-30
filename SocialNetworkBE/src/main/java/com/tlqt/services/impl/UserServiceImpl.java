/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlqt.pojo.User;
import com.tlqt.repositories.UserRepository;
import com.tlqt.services.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<User> getUsers() {
        return userRepo.getUsers();
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passEncoder.encode(user.getPassword()));

        if (!user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.userRepo.addUser(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid username!");
        }

        //chi dinh truong UserRole
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRoleId().getRoleName()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepo.getUserByEmail(email);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return this.userRepo.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public List<Object[]> getAlumniAndRelatedInfo() {
        return this.userRepo.getAlumniAndRelatedInfo();

    }

    @Override
    public List<Object[]> getLecturersAndRelatedInfo() {
        return this.userRepo.getLecturersAndRelatedInfo();
    }

    @Override
    public User getAuthorByPostId(int postId) {
        return this.userRepo.getAuthorByPostId(postId);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public User getUserByCommentId(int commentId) {

        return userRepo.getUserByCommentId(commentId);
    }

    @Override
    public void update(User user, Boolean newPass) {
        if (newPass) {
            user.setPassword(passEncoder.encode(user.getPassword()));
        }

        if (user.getFile() != null && !user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (user.getFile1() != null && !user.getFile1().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile1().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setBackground(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        userRepo.update(user);
    }

    public List<User> getUsersByUsername(String queryName) {
        return userRepo.getUsersByUsername(queryName);
    }

    @Override
    public List<Object[]> countUsersByYear(int startYear, int endYear) {
        return userRepo.countUsersByYear(startYear, endYear);
    }

    @Override
    public List<Object[]> countUsersByMonth(int year) {
        return userRepo.countUsersByMonth(year);
    }

    @Override
    public List<Object[]> countUsersByQuarter(int year) {
        return userRepo.countUsersByQuarter(year);

    }
}
