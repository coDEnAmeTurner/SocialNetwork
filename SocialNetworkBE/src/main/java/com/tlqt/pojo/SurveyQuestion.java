/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "survey_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestion.findAll", query = "SELECT s FROM SurveyQuestion s"),
    @NamedQuery(name = "SurveyQuestion.findById", query = "SELECT s FROM SurveyQuestion s WHERE s.id = :id")})
public class SurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    
    @JsonIgnore
    @JoinColumn(name = "survey_id", referencedColumnName = "post_id")
    @ManyToOne(optional = false)
    private Survey surveyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyQuestionId")
    @JsonIgnore
    private Set<Choice> choiceSet;

    public SurveyQuestion() {
    }

    public SurveyQuestion(Integer id) {
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

    public Survey getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId) {
        this.surveyId = surveyId;
    }

    @XmlTransient
    public Set<Choice> getChoiceSet() {
        return choiceSet;
    }

    public void setChoiceSet(Set<Choice> choiceSet) {
        this.choiceSet = choiceSet;
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
        if (!(object instanceof SurveyQuestion)) {
            return false;
        }
        SurveyQuestion other = (SurveyQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.SurveyQuestion[ id=" + id + " ]";
    }
    
}
