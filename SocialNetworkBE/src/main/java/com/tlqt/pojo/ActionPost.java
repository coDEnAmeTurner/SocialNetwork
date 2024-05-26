/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "action_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionPost.findAll", query = "SELECT a FROM ActionPost a"),
    @NamedQuery(name = "ActionPost.findByUserId", query = "SELECT a FROM ActionPost a WHERE a.actionPostPK.userId = :userId"),
    @NamedQuery(name = "ActionPost.findByPostId", query = "SELECT a FROM ActionPost a WHERE a.actionPostPK.postId = :postId"),
    @NamedQuery(name = "ActionPost.findByCounts", query = "SELECT a FROM ActionPost a WHERE a.counts = :counts")})
public class ActionPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActionPostPK actionPostPK;
    @Column(name = "counts")
    private Integer counts;
    @JoinColumn(name = "action_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Action actionId;
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Post post;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public ActionPost() {
    }

    public ActionPost(ActionPostPK actionPostPK) {
        this.actionPostPK = actionPostPK;
    }

    public ActionPost(int userId, int postId) {
        this.actionPostPK = new ActionPostPK(userId, postId);
    }

    public ActionPostPK getActionPostPK() {
        return actionPostPK;
    }

    public void setActionPostPK(ActionPostPK actionPostPK) {
        this.actionPostPK = actionPostPK;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Action getActionId() {
        return actionId;
    }

    public void setActionId(Action actionId) {
        this.actionId = actionId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actionPostPK != null ? actionPostPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActionPost)) {
            return false;
        }
        ActionPost other = (ActionPost) object;
        if ((this.actionPostPK == null && other.actionPostPK != null) || (this.actionPostPK != null && !this.actionPostPK.equals(other.actionPostPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.ActionPost[ actionPostPK=" + actionPostPK + " ]";
    }
    
}
