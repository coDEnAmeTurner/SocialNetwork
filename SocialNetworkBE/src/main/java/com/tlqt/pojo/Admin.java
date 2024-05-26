/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findByUserId", query = "SELECT a FROM Admin a WHERE a.user = :user")})
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User user;
    @OneToMany(mappedBy = "dispatchingAdmin")
    private Set<Lecturer> lecturerSet;


    public Admin() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Set<Lecturer> getLecturerSet() {
        return lecturerSet;
    }

    public void setLecturerSet(Set<Lecturer> lecturerSet) {
        this.lecturerSet = lecturerSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user.getId() != null ? user.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.user.getId() == null && other.user.getId() != null) || (this.user.getId() != null && !this.user.getId().equals(other.user.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.Admin[ userId=" + user.getId() + " ]";
    }

    
}
