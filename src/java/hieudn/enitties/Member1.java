/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.enitties;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Member", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByMemberId", query = "SELECT m FROM Member1 m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "Member1.findByMemberPassword", query = "SELECT m FROM Member1 m WHERE m.memberPassword = :memberPassword"),
    @NamedQuery(name = "Member1.findByMemberFullname", query = "SELECT m FROM Member1 m WHERE m.memberFullname = :memberFullname"),
    @NamedQuery(name = "Member1.findByRole", query = "SELECT m FROM Member1 m WHERE m.role = :role"),
    @NamedQuery(name = "Member1.findByMemberStatus", query = "SELECT m FROM Member1 m WHERE m.memberStatus = :memberStatus")})
public class Member1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "memberId", nullable = false, length = 30)
    private String memberId;
    @Basic(optional = false)
    @Column(name = "memberPassword", nullable = false, length = 100)
    private String memberPassword;
    @Basic(optional = false)
    @Column(name = "memberFullname", nullable = false, length = 100)
    private String memberFullname;
    @Basic(optional = false)
    @Column(name = "role", nullable = false, length = 10)
    private String role;
    @Basic(optional = false)
    @Column(name = "memberStatus", nullable = false, length = 10)
    private String memberStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Article> articleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Emotion> emotionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private Collection<Notification> notificationCollection;

    public Member1() {
    }

    public Member1(String memberId) {
        this.memberId = memberId;
    }

    public Member1(String memberId, String memberPassword, String memberFullname) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberFullname = memberFullname;
        this.role = "member";
        this.memberStatus = "new";
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberFullname() {
        return memberFullname;
    }

    public void setMemberFullname(String memberFullname) {
        this.memberFullname = memberFullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @XmlTransient
    public Collection<Emotion> getEmotionCollection() {
        return emotionCollection;
    }

    public void setEmotionCollection(Collection<Emotion> emotionCollection) {
        this.emotionCollection = emotionCollection;
    }

    @XmlTransient
    public Collection<Notification> getNotificationCollection() {
        return notificationCollection;
    }

    public void setNotificationCollection(Collection<Notification> notificationCollection) {
        this.notificationCollection = notificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.enitties.Member1[ memberId=" + memberId + " ]";
    }

}
