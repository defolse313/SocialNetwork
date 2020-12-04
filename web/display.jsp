<%-- 
    Document   : display
    Created on : Sep 23, 2020, 1:36:25 PM
    Author     : hp
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="display.css">
        <title>JSP Page</title>
    </head>
    <body class="all">
        <header class="head" id="heading">
            <h1 class="acc">Hello ${sessionScope.memberFullname}</h1>
            <div class="same">
                <form action="MainController" method="POST" class="wsearch">
                    <img src="images/Free_Sample_By_Wix.jpg" style="width: 80px; height: 80px; top: 0" class="img1"/>
                    <input type="text" name="txtSearch" placeholder="Find the articles contents" class="search"/>
                    <div class="icon-wrap" style="background-clip: #fff">
                        <button type="submit" value="Search" name="action" class="btn">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 -3 30 24" >
                            <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                            </svg>
                        </button>
                    </div>
                    <div class="logout">
                        <button type="submit" value="Log Out" name="action" class="lo">Log Out</button>
                    </div>
                </form>
            </div>
        </header>
        <div class="bodyy" id="bod">
            <c:if test="${requestScope.INFO != null}">
                <c:if test="${not empty requestScope.INFO}" var="checkList">
                    <c:forEach var="dto" items="${requestScope.INFO}" varStatus="counter">
                        <form action="MainController" method="POST" class="form1" style="display: inline-block">
                            <div class="div">
                                <input type="hidden" name="txtArticle" value="${dto.articleID}"/>
                                <button type="submit" value="Details" name="action" class="dot">
                                    <div class="content">
                                        <div class="contents">${dto.articleDate}</div>
                                        <div class="title">${dto.articleTitle}</div>
                                        <div class="author">Author: ${dto.memberId.memberFullname}</div>
                                    </div>

                                          <div class="img"><img src="images/${dto.articleImage}" width="100%" height="100%" class="imggg"></div>
    
                                <input type="hidden" name="articleID" value="${dto.articleID}"/>
                            </button>
                        </div>
                    </form>
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
                </c:forEach>
            </c:if>
            <c:if test="${!checkList}">
                No record found
            </c:if>
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
    <div class="paging">
        <c:forEach begin="1" end="${requestScope.ARTICLE_COUNT}" var="page" varStatus="counter">
            <c:url value="SearchController" var="pageNum">
                <c:param name="pageIDPaging" value="${counter.count}"/>
                <c:param name="txtSearch" value="${param.txtSearch}"/>
            </c:url>
            <a href="${pageScope.pageNum}">
                <span class="page">${page}</span></a>
            </c:forEach>
    </div>
    <button onclick="myFunction()" class="clickPost" 
            <c:if test="${sessionScope.ROLE eq 'admin'}">disabled="true"</c:if>>
        <img src="images/postIcon.png" style="width: 100%; height: 100%">
    </button> 
    <div id="myDIV">
        <div class="pTitle">JOIN OUR COMMUNITY BY POSTING ANY OF YOUR INTERESTING ARTICLES</div>
        <form action="MainController" method="POST" class = "formPost" enctype="multipart/form-data">
            <input class="f1" type="text" name="txtArticleTitle" placeholder="an article would be more attractive with an interesting title..."/><br/>
            <input class="f2" type="text" name="txtArticleContent" placeholder="what are you thinking, my dude?"/><br/>
            <div class="file">
                <div style="color: #f9f8f6">Finish your article with an amazing image:</div><br/> 
                <div>
                    <label class="lbl">
                        <input type="file" name="txtFile"/>
                        Upload Image
                    </label></div>
            </div>
            <button class="sm" type="submit" name="action" value="Post">POST</button>
        </form>
    </div>

    <script>
        function myFunction() {
            var x = document.getElementById("myDIV");
            if (x.style.display === "none") {
                x.style.display = "block";
                document.getElementById("heading").style.opacity = "0.1";
                document.getElementById("heading").style.background = "black";
                document.getElementById("bod").style.opacity = "0.1";
                document.getElementById("bod").style.background = "black";
            } else {
                x.style.display = "none";
                document.getElementById("heading").style.opacity = "1";
                document.getElementById("bod").style.opacity = "1";
                document.getElementById("heading").style.background = "#7c9982";
                document.getElementById("bod").style.background = "#f9f8f6";
            }
        }
    </script>

</body> 
</html>
