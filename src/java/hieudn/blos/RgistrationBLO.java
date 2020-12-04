/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.blos;

import hieudn.enitties.Article;
import hieudn.enitties.Comment;
import hieudn.enitties.Emotion;
import hieudn.enitties.Member1;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
//@Entity
//@Table(name="RgistrationBLO", catalog = "")
public class RgistrationBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SocialNetworkPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public boolean checkLogin(String username, String password) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select m From Member1 m Where m.memberId = :memberId And m.memberPassword = :memberPassword";
        Query query = em.createQuery(jpql);
        query.setParameter("memberId", username);
        query.setParameter("memberPassword", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Member1 loginPage(String email) {
        Member1 acc = null;
        EntityManager em = emf.createEntityManager();
        Query queryLogin = em.createNamedQuery("Member1.findByMemberId");
        queryLogin.setParameter("memberId", email);
        try {
            acc = (Member1) queryLogin.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean insertAccount(String memberId, String fullname, String password) {
        EntityManager em = emf.createEntityManager();
        Member1 mem = em.find(Member1.class, memberId);
        if (mem == null) {
            mem = new Member1(memberId, password, fullname);
            em.getTransaction().begin();
            em.persist(mem);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public List searchByContent(String search, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Article.findByArticleContent";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleContent", "%" + search + "%");
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;
    }

    public int getAmount(String search, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(articleID) from Article where articleContent like ? AND articleStatus = 'ACTIVE'";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", "%" + search + "%");
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public Article searchById(int search) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Article.findByArticleID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleID", search);
        Article a = (Article) query.getSingleResult();
        return a;
    }

    public List<Emotion> getLove(Article a) {
        List<Emotion> result = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "Emotion.getLove";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleId", a);
        result = query.getResultList();
        return result;
    }

    public List<Emotion> getDislike(Article a) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Emotion.getDislike";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleId", a);
        List<Emotion> result = query.getResultList();
        return result;
    }

    public List<Comment> getComment(Article articleId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Comment.findComment";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleId", articleId);
        List<Comment> result = query.getResultList();
        System.out.println("result: " + result);
        return result;
    }

    public String reactionSeperate(int articleId, String memberId) {
        EntityManager em = emf.createEntityManager();
        String sql = "SELECT emotion From Emotion WHERE articleId = ? AND memberId = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, articleId);
        query.setParameter(2, memberId);
        try {
            String e = (String) query.getSingleResult();
            return e;
        } catch (NoResultException e1) {
            return null;
        }
    }

    public boolean insertReact(String emotion, int articleId, String memberId) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO Emotion(articleId, memberId, emotion) VALUES (?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, articleId);
        query.setParameter(2, memberId);
        query.setParameter(3, emotion);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean updateReact(String emotion, int articleId, String memberId) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "UPDATE Emotion set emotion = ? WHERE articleId = ? AND memberId = ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, emotion);
        query.setParameter(2, articleId);
        query.setParameter(3, memberId);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean cmt(int articleId, String memberId, String commentContent, Date commentDate) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        String sql = "INSERT INTO Comment(articleId, memberId, commentContent, commentDate) VALUES (?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, articleId);
        query.setParameter(2, memberId);
        query.setParameter(3, commentContent);
        query.setParameter(4, commentDate);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;

    }

    public boolean createArticle(String memberId, String articleTitle, String articleContent, String articleImage, Date articleDate, String articleStatus) {
        boolean check = false;
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO Article( memberId, articleTitle, articleContent, articleImage, articleDate, articleStatus) VALUES (?, ?, ?, ?, ?, ?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, memberId);
        query.setParameter(2, articleTitle);
        query.setParameter(3, articleContent);
        query.setParameter(4, articleImage);
        query.setParameter(5, articleDate);
        query.setParameter(6, articleStatus);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean deletePost(int articleID, String articleStatus) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        String sql = "Update Article set articleStatus = ? WHERE articleID = ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, articleStatus);
        query.setParameter(2, articleID);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public boolean deleteCmt(int commentId) {
        EntityManager em = emf.createEntityManager();
        Comment c = em.find(Comment.class, commentId);
        if (c != null) {
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
