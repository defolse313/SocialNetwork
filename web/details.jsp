<%-- 
    Document   : details.jsp
    Created on : Sep 26, 2020, 11:37:34 AM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="details.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
        </h1>
        <form action="MainController" method="POST">
            <button class="dtl" type="submit" value="Back" name="action"><img src="images/Free_Sample_By_Wix.jpg" style="width: 100%; height: 100%; top: 0" class="img1"/></button>
        </form>
        <div class="details">
            <div class="detailsContent">
                <div class="top">
                    <c:if test="${not empty requestScope.ARTICLE}">
                        <div class="time">${requestScope.ARTICLE.articleDate}</div>
                        <div class="dTitle">${requestScope.ARTICLE.articleTitle}</div>
                        <div class="dContent">${requestScope.ARTICLE.articleContent}</div>
                    </div>
                    <form id="del" action="MainController" method="POST">
                        <c:if test="${sessionScope.ROLE eq 'admin'}">
                            <input type="hidden" name="articleID" value="${dto.articleID}"/>
                            <input type="hidden" name="action" value="Delete"/>
                            <button class="del" type="button"  onclick="checkDel()">Delete</button>
                        </c:if>
                        <c:if test="${(sessionScope.ROLE eq 'member') && (dto.memberId.memberId == sessionScope.ID)}">
                            <input type="hidden" name="articleID" value="${dto.articleID}"/>
                            <input type="hidden" name="action" value="Delete"/>
                            <button class="del" type="button" onclick="checkDel()">Delete</button>
                        </c:if> 
                    </form>
                    <div class="react">
                        <form action="InsertEmoController" method="POST">
                            <button type="submit" class="love" value="LOVE" name="action"
                                    <c:if test="${sessionScope.ROLE eq 'admin'}">disabled="true"</c:if>>
                                ${requestScope.LOVE} LOVE
                            </button>
                            <button type="submit" class="dl" value="DISLIKE" name="action"
                                    <c:if test="${sessionScope.ROLE eq 'admin'}">disabled="true"</c:if>>
                                ${requestScope.DISLIKE} DISLIKE
                            </button>
                            <input type="hidden" name="txtArticle" value="${requestScope.ARTICLE.articleID}">
                        </form>
                    </div>
                    <div class="cmt">
                        <c:if test="${not empty requestScope.CMT}">
                            <c:forEach var="dto" items="${requestScope.CMT}">
                                <div class="cmtdetails">
                                    <div class="cmtCnt">${dto.commentContent}</div>
                                    <div class="cmtInf">by ${dto.memberId.memberFullname}</div>
                                    <div class="cmtTime">at ${dto.commentDate}</div>
                                    <form action="MainController" method="POST" class="frmDel">
                                        <div>
                                            <c:if test="${dto.memberId.memberId eq sessionScope.ID}">
                                                <button type="submit" class="btnDel" value="CmtDel" name="action"><img src="images/close.png" style="width: 100%; height: 100%"></button>
                                                <input type="hidden" name="deletee" value="${dto.commentId}"/>
                                                <input type="hidden" name="txtArticle" value="${requestScope.ARTICLE.articleID}">
                                            </c:if>
                                        </div>
                                    </form>
                                </div>
                            </c:forEach> 
                        </c:if>
                    </div>
                    <div class="crtCmt">
                        <form action="MainController" method="POST" class="fcmt">
                            <input class="cmtdts" type="text" name="txtComment" placeholder="write a comment..." 
                                   <c:if test="${sessionScope.ROLE eq 'admin'}">disabled="true"</c:if>/>
                                   <button class="btnCmt" type="submit" value="Comment" name="action"
                                   <c:if test="${sessionScope.ROLE eq 'admin'}">disabled="true"</c:if>>
                                       Post
                                   </button> 
                                   <input type="hidden" name="txtArticle" value="${requestScope.ARTICLE.articleID}">
                        </form>
                    </div>
                </div>
                <div class="detailsImage">
                    <img src="images/${requestScope.ARTICLE.articleImage}" height="100%" class="imgg">
                </div>
            </c:if>
        </div>
        <script>
            function checkDel() {
                let confirmed = confirm("Do you really want to remove this article?");
                if (confirmed) {
                    document.getElementById("del").submit();
                }
            }
        </script>
    </body>

</html>
