/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "typical_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypicalUser.findAll", query = "SELECT t FROM TypicalUser t"),
    @NamedQuery(name = "TypicalUser.findByUserId", query = "SELECT t FROM TypicalUser t WHERE t.user = :user")})
public class TypicalUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "typicalUser")
    private Alumnus alumnus;
    @JoinColumn(name = "academic_rank_id", referencedColumnName = "id")
    @ManyToOne
    private AcademicRank academicRankId;
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Degree degreeId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "typicalUser")
    private Lecturer lecturer;

    public TypicalUser() {
    }

    public void setAlumnus(Alumnus alumnus) {
        this.alumnus = alumnus;
    }

    public AcademicRank getAcademicRankId() {
        return academicRankId;
    }

    public void setAcademicRankId(AcademicRank academicRankId) {
        this.academicRankId = academicRankId;
    }

    public Degree getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Degree degreeId) {
        this.degreeId = degreeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getUser().getId() != null ? getUser().getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypicalUser)) {
            return false;
        }
        TypicalUser other = (TypicalUser) object;
        if ((this.getUser().getId() == null && other.getUser().getId() != null) || (this.getUser().getId() != null && !this.user.getId().equals(other.user.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.TypicalUser[ userId=" + getUser().getId() + " ]";
    }

    /**
     * @return the alumnus
     */
    public Alumnus getAlumnus() {
        return alumnus;
    }
    
}
