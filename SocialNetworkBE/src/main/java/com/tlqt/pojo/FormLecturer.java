/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import com.tlqt.validators.CheckDateFormat;
import com.tlqt.validators.FileNotFound;
import com.tlqt.validators.InvalidImage;
import com.tlqt.validators.NotNull;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class FormLecturer {

    private Integer id;

    @NotNull(message = "Fullname can't be empty!!!")
    @Size(max = 100)
    private String fullName;

    @NotNull(message = "username can't be empty!!!")
    @Size(min = 1, max = 50)
    private String username;

    @NotNull(message = "Dob can't be empty!!!")
    @CheckDateFormat(pattern = "yyyy-MM-dd", message = "Invalid date (yyyy-MM-dd)")
    private String dob;

    @NotNull(message = "email can't be empty!!!")
    @Lob
    @Size(min = 1, max = 2147483647)
    private String email;

    @Size(min = 9, max = 11)
    @NotNull(message = "phone can't be empty!!!")
    private String phone;

    private String academicRankId;

    @NotNull(message = "degreeId can't be empty!!!")
    private String degreeId;

    @NotNull(message = "titleId can't be empty!!!")
    private String titleId;

    @Basic(optional = false)
    @FileNotFound
    @InvalidImage(type = {"image/jpeg", "image/png", "image/jpg", "image/webp"}, message = "Only jpeg, png, jpg, webp allowed!!!")
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
    public String getAcademicRankId() {
        return academicRankId;
    }

    /**
     * @param academicRankId the academicRankId to set
     */
    public void setAcademicRankId(String academicRankId) {
        this.academicRankId = academicRankId;
    }

    /**
     * @return the degreeId
     */
    public String getDegreeId() {
        return degreeId;
    }

    /**
     * @param degreeId the degreeId to set
     */
    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    /**
     * @return the titleId
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * @param titleId the titleId to set
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
}
