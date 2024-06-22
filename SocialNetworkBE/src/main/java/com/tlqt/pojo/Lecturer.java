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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "lecturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findByTypicalUserId", query = "SELECT l FROM Lecturer l WHERE l.typicalUserId = :typicalUserId"),
    @NamedQuery(name = "Lecturer.findByLocked", query = "SELECT l FROM Lecturer l WHERE l.locked = :locked")})
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "typical_user_id")
    private Integer typicalUserId;
    @Column(name = "locked")
    private Boolean locked;
    
    @JoinColumn(name = "dispatching_admin_id", referencedColumnName = "user_id")
    @ManyToOne
    private Admin dispatchingAdminId;
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Title titleId;
    @JoinColumn(name = "typical_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private TypicalUser typicalUser;

    public Lecturer() {
    }

    public Lecturer(Integer typicalUserId) {
        this.typicalUserId = typicalUserId;
    }

    public Integer getTypicalUserId() {
        return typicalUserId;
    }

    public void setTypicalUserId(Integer typicalUserId) {
        this.typicalUserId = typicalUserId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Admin getDispatchingAdminId() {
        return dispatchingAdminId;
    }

    public void setDispatchingAdminId(Admin dispatchingAdminId) {
        this.dispatchingAdminId = dispatchingAdminId;
    }

    public Title getTitleId() {
        return titleId;
    }

    public void setTitleId(Title titleId) {
        this.titleId = titleId;
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
        hash += (typicalUserId != null ? typicalUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lecturer)) {
            return false;
        }
        Lecturer other = (Lecturer) object;
        if ((this.typicalUserId == null && other.typicalUserId != null) || (this.typicalUserId != null && !this.typicalUserId.equals(other.typicalUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.Lecturer[ typicalUserId=" + typicalUserId + " ]";
    }
    
}
