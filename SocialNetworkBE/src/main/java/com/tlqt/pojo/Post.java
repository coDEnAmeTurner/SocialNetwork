/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p"),
    @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id"),
    @NamedQuery(name = "Post.findByTitle", query = "SELECT p FROM Post p WHERE p.title = :title"),
    @NamedQuery(name = "Post.findByUnlocked", query = "SELECT p FROM Post p WHERE p.unlocked = :unlocked")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 200)
    @Column(name = "title")
    private String title;
    @Column(name = "unlocked")
    private Boolean unlocked;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    private Invitation invitation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postId")
    private Set<SurveyQuestion> surveyQuestionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<ActionPost> actionPostSet;
    @JoinColumn(name = "content_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContentType contentTypeId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(mappedBy = "postId")
    private Set<Comment> commentSet;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    private TypicalPost typicalPost;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    @XmlTransient
    public Set<SurveyQuestion> getSurveyQuestionSet() {
        return surveyQuestionSet;
    }

    public void setSurveyQuestionSet(Set<SurveyQuestion> surveyQuestionSet) {
        this.surveyQuestionSet = surveyQuestionSet;
    }

    @XmlTransient
    public Set<ActionPost> getActionPostSet() {
        return actionPostSet;
    }

    public void setActionPostSet(Set<ActionPost> actionPostSet) {
        this.actionPostSet = actionPostSet;
    }

    public ContentType getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(ContentType contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    public TypicalPost getTypicalPost() {
        return typicalPost;
    }

    public void setTypicalPost(TypicalPost typicalPost) {
        this.typicalPost = typicalPost;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.Post[ id=" + id + " ]";
    }
    
}
