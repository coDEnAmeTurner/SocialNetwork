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
import javax.persistence.Lob;
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
@Table(name = "typical_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypicalPost.findAll", query = "SELECT t FROM TypicalPost t"),
    @NamedQuery(name = "TypicalPost.findByPostId", query = "SELECT t FROM TypicalPost t WHERE t.postId = :postId")})
public class TypicalPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "post_id")
    private Integer postId;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Post post;

    public TypicalPost() {
    }

    public TypicalPost(Integer postId) {
        this.postId = postId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postId != null ? postId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypicalPost)) {
            return false;
        }
        TypicalPost other = (TypicalPost) object;
        if ((this.postId == null && other.postId != null) || (this.postId != null && !this.postId.equals(other.postId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tlqt.pojo.TypicalPost[ postId=" + postId + " ]";
    }
    
}
