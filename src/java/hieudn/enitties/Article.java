/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.enitties;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Article", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticleID", query = "SELECT a FROM Article a WHERE a.articleID = :articleID"),
    @NamedQuery(name = "Article.findByArticleTitle", query = "SELECT a FROM Article a WHERE a.articleTitle = :articleTitle"),
    @NamedQuery(name = "Article.findByArticleContent", query = "SELECT a FROM Article a WHERE a.articleContent LIKE :articleContent AND a.articleStatus = 'ACTIVE' order by a.articleDate DESC"),
    @NamedQuery(name = "Article.findByArticleImage", query = "SELECT a FROM Article a WHERE a.articleImage = :articleImage"),
    @NamedQuery(name = "Article.findByArticleDate", query = "SELECT a FROM Article a WHERE a.articleDate = :articleDate"),
    @NamedQuery(name = "Article.findByArticleStatus", query = "SELECT a FROM Article a WHERE a.articleStatus = :articleStatus")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "articleID", nullable = false)
    private Integer articleID;
    @Basic(optional = false)
    @Column(name = "articleTitle", nullable = false, length = 200)
    private String articleTitle;
    @Basic(optional = false)
    @Column(name = "articleContent", nullable = false, length = 1073741823)
    private String articleContent;
    @Column(name = "articleImage", length = 1073741823)
    private String articleImage;
    @Basic(optional = false)
    @Column(name = "articleDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date articleDate;
    @Basic(optional = false)
    @Column(name = "articleStatus", nullable = false, length = 10)
    private String articleStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleId")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "memberId", referencedColumnName = "memberId", nullable = false)
    @ManyToOne(optional = false)
    private Member1 memberId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleId")
    private Collection<Emotion> emotionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articleId")
    private Collection<Notification> notificationCollection;

    public Article() {
    }

    public Article(Integer articleID) {
        this.articleID = articleID;
    }

    public Article(Integer articleID, String articleTitle, String articleContent, String articleImage, Date articleDate, String articleStatus, Member1 memberId) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
        this.memberId = memberId;
    }

    public Article(String articleTitle, String articleContent, String articleImage, Date articleDate, String articleStatus, Member1 memberId) {
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
        this.memberId = memberId;
    }

    
    public Article(Integer articleID, String articleTitle, String articleContent, Date articleDate, String articleStatus) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleDate = articleDate;
        this.articleStatus = articleStatus;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Member1 getMemberId() {
        return memberId;
    }

    public void setMemberId(Member1 memberId) {
        this.memberId = memberId;
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
        hash += (articleID != null ? articleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleID == null && other.articleID != null) || (this.articleID != null && !this.articleID.equals(other.articleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.enitties.Article[ articleID=" + articleID + " ]";
    }

}
