<%-- 
    Document   : login
    Created on : Sep 21, 2020, 9:09:32 AM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="body"></div>
        <div class="grad"></div>
        <div class="header">
            <div>Kind<span>Ai</span></div>
        </div>
        <br/>
        <div class="login" id="login">
            <form action="MainController" method="POST">
                <div class ="bg" style="display: flex; justify-content: center; align-items: center; flex-direction: column; padding-top: 1rem">
                    <!--<br/><br/><br/><br/>-->
                    &emsp; &emsp; &emsp; &emsp; <input type ="text" name="txtMemberId" placeholder="Email" required=""> <br/>
                    <font style="color: white; font-size: 20px">${requestScope.INVALID}</font>
                    &emsp; &emsp; &emsp; &emsp; <input type="password" name="txtPassword" placeholder="Password" required=""><br/>
                    &emsp; &emsp; &emsp; &emsp; <input type="submit" value="Log In" name="action" class="submit"/><br/>
                    <br/><br/>
                    <div class="linking">Dont have an account? <a href="register.jsp" class="link">Sign up</a></div><br/>
                    <font style="color: white; font-size: 20px">${requestScope.INVALID1}</font>
                    <br/><br/><br/><br/>
                </div>
            </form>
        </div>
    </body>
</html>
