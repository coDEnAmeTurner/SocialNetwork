/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import com.tlqt.validators.CheckDateFormat;
import com.tlqt.validators.FileNotFound;
import com.tlqt.validators.InvalidImage;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class FormLecturer {

    private Integer id;

    @Size(max = 100)
    private String fullName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @Basic(optional = false)
    @NotNull
    @CheckDateFormat(pattern = "yyyy-MM-dd", message = "Invalid date (yyyy-MM-dd)")
    private String dob;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    private String email;
    
    @Size(max = 10)
    private String phone;

    @Basic(optional = false)
    @NotNull
    private int academicRankId;

    @Basic(optional = false)
    @NotNull
    private int degreeId;

    @Basic(optional = false)
    @NotNull
    private int titleId;

    @Basic(optional = false)
    @FileNotFound
    @InvalidImage(type = {"image/jpeg","image/png","image/jpg","image/webp"}, message = "Only jpeg, png, jpg, webp allowed!!!")
    private MultipartFile file;

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the academicRankId
     */
    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the academicRankId
     */
    public int getAcademicRankId() {
        return academicRankId;
    }

    /**
     * @param academicRankId the academicRankId to set
     */
    public void setAcademicRankId(int academicRankId) {
        this.academicRankId = academicRankId;
    }

    /**
     * @return the degreeId
     */
    public int getDegreeId() {
        return degreeId;
    }

    /**
     * @param degreeId the degreeId to set
     */
    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    /**
     * @return the titleId
     */
    public int getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }
}
