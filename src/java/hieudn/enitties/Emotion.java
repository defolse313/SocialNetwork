/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.enitties;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Emotion", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emotion.findAll", query = "SELECT e FROM Emotion e"),
    @NamedQuery(name = "Emotion.findByEmotionId", query = "SELECT e FROM Emotion e WHERE e.emotionId = :emotionId"),
    @NamedQuery(name = "Emotion.findByEmotion", query = "SELECT e FROM Emotion e WHERE e.emotion = :emotion"),
    @NamedQuery(name = "Emotion.getLove", query = "SELECT e from Emotion e where e.articleId = :articleId AND e.emotion = 'LOVE'"),
    @NamedQuery(name = "Emotion.getDislike", query = "SELECT e from Emotion e where e.articleId = :articleId AND e.emotion = 'DISLIKE'")})

public class Emotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "emotionId", nullable = false)
    private Integer emotionId;
    @Basic(optional = false)
    @Column(name = "emotion", nullable = false)
    private String emotion;
    @JoinColumn(name = "articleId", referencedColumnName = "articleID", nullable = false)
    @ManyToOne(optional = false)
    private Article articleId;
    @JoinColumn(name = "memberId", referencedColumnName = "memberId", nullable = false)
    @ManyToOne(optional = false)
    private Member1 memberId;

    public Emotion() {
    }

    public Emotion(Integer emotionId) {
        this.emotionId = emotionId;
    }

    public Emotion(Integer emotionId, String emotion, Article articleId, Member1 memberId) {
        this.emotionId = emotionId;
        this.emotion = emotion;
        this.articleId = articleId;
        this.memberId = memberId;
    }

    
    public Emotion(String emotion, Article articleId, Member1 memberId) {
        this.emotion = emotion;
        this.articleId = articleId;
        this.memberId = memberId;
    }
    
    
    public Emotion(Integer emotionId, String emotion) {
        this.emotionId = emotionId;
        this.emotion = emotion;
    }

    public Integer getEmotionId() {
        return emotionId;
    }

    public void setEmotionId(Integer emotionId) {
        this.emotionId = emotionId;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public Member1 getMemberId() {
        return memberId;
    }

    public void setMemberId(Member1 memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emotionId != null ? emotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emotion)) {
            return false;
        }
        Emotion other = (Emotion) object;
        if ((this.emotionId == null && other.emotionId != null) || (this.emotionId != null && !this.emotionId.equals(other.emotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.enitties.Emotion[ emotionId=" + emotionId + " ]";
    }

}
