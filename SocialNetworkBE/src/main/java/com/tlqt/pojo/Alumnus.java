/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "alumnus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumnus.findAll", query = "SELECT a FROM Alumnus a"),
    @NamedQuery(name = "Alumnus.findByTypicalUserId", query = "SELECT a FROM Alumnus a WHERE a.typicalUser = :typicalUser"),
    @NamedQuery(name = "Alumnus.findByStudentId", query = "SELECT a FROM Alumnus a WHERE a.studentId = :studentId")})
public class Alumnus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "student_id")
    private String studentId;
    @Id
    @JoinColumn(name = "typical_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TypicalUser typicalUser;

    public Alumnus() {
    }

    public Alumnus(String studentId, TypicalUser typicalUser) {
        this.studentId = studentId;
        this.typicalUser = typicalUser;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public TypicalUser getTypicalUser() {
        return typicalUser;
    }

    public void setTypicalUser(TypicalUser typicalUser) {
        this.typicalUser = typicalUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typicalUser.getUser().getId() != null ? typicalUser.getUser().getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alumnus)) {
            return false;
        }
        Alumnus other = (Alumnus) object;
        if ((this.typicalUser.getUser().getId() == null && other.typicalUser.getUser().getId() != null) || (this.typicalUser.getUser().getId() != null && !this.typicalUser.getUser().getId().equals(other.typicalUser.getUser().getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.Alumnus[ typicalUserId=" + typicalUser.getUser().getId() + " ]";
    }
    
}
