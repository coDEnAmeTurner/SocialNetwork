/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "academic_rank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicRank.findAll", query = "SELECT a FROM AcademicRank a"),
    @NamedQuery(name = "AcademicRank.findById", query = "SELECT a FROM AcademicRank a WHERE a.id = :id"),
    @NamedQuery(name = "AcademicRank.findByAcademicRankName", query = "SELECT a FROM AcademicRank a WHERE a.academicRankName = :academicRankName")})
public class AcademicRank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "academic_rank_name")
    private String academicRankName;
    @JsonIgnore
    @OneToMany(mappedBy = "academicRankId")
    private Set<TypicalUser> typicalUserSet;

    public AcademicRank() {
    }

    public AcademicRank(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcademicRankName() {
        return academicRankName;
    }

    public void setAcademicRankName(String academicRankName) {
        this.academicRankName = academicRankName;
    }

    @XmlTransient
    public Set<TypicalUser> getTypicalUserSet() {
        return typicalUserSet;
    }

    public void setTypicalUserSet(Set<TypicalUser> typicalUserSet) {
        this.typicalUserSet = typicalUserSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcademicRank)) {
            return false;
        }
        AcademicRank other = (AcademicRank) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.AcademicRank[ id=" + id + " ]";
    }
    
}
