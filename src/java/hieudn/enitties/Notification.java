/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.enitties;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Notification", catalog = "SocialNetwork", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByNotiId", query = "SELECT n FROM Notification n WHERE n.notiId = :notiId"),
    @NamedQuery(name = "Notification.findByNotiDate", query = "SELECT n FROM Notification n WHERE n.notiDate = :notiDate")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "notiId", nullable = false)
    private Integer notiId;
    @Basic(optional = false)
    @Column(name = "notiDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date notiDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "notiDescription", nullable = false, length = 2147483647)
    private String notiDescription;
    @JoinColumn(name = "articleId", referencedColumnName = "articleID", nullable = false)
    @ManyToOne(optional = false)
    private Article articleId;
    @JoinColumn(name = "memberId", referencedColumnName = "memberId", nullable = false)
    @ManyToOne(optional = false)
    private Member1 memberId;

    public Notification() {
    }

    public Notification(Integer notiId) {
        this.notiId = notiId;
    }

    public Notification(Integer notiId, Date notiDate, String notiDescription) {
        this.notiId = notiId;
        this.notiDate = notiDate;
        this.notiDescription = notiDescription;
    }

    public Integer getNotiId() {
        return notiId;
    }

    public void setNotiId(Integer notiId) {
        this.notiId = notiId;
    }

    public Date getNotiDate() {
        return notiDate;
    }

    public void setNotiDate(Date notiDate) {
        this.notiDate = notiDate;
    }

    public String getNotiDescription() {
        return notiDescription;
    }

    public void setNotiDescription(String notiDescription) {
        this.notiDescription = notiDescription;
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
        hash += (notiId != null ? notiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notiId == null && other.notiId != null) || (this.notiId != null && !this.notiId.equals(other.notiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.enitties.Notification[ notiId=" + notiId + " ]";
    }
    
}
