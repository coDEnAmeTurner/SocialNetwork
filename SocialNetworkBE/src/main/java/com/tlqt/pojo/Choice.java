/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "choice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choice.findAll", query = "SELECT c FROM Choice c"),
    @NamedQuery(name = "Choice.findById", query = "SELECT c FROM Choice c WHERE c.id = :id"),
    @NamedQuery(name = "Choice.findByContent", query = "SELECT c FROM Choice c WHERE c.content = :content"),
    @NamedQuery(name = "Choice.findByVoteCount", query = "SELECT c FROM Choice c WHERE c.voteCount = :voteCount")})
public class Choice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "content")
    private String content;
    @Column(name = "voteCount")
    private Integer voteCount;
    @JoinColumn(name = "survey_question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SurveyQuestion surveyQuestionId;

    public Choice() {
    }

    public Choice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public SurveyQuestion getSurveyQuestionId() {
        return surveyQuestionId;
    }

    public void setSurveyQuestionId(SurveyQuestion surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
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
        if (!(object instanceof Choice)) {
            return false;
        }
        Choice other = (Choice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.Choice[ id=" + id + " ]";
    }
    
}
