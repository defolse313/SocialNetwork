<%-- 
    Document   : member
    Created on : Sep 21, 2020, 1:05:00 PM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.memberFullname}</h1>
        <br/>
        <form action="MainController" method="POST">
            <input type="submit" value="Log Out" name="action">
        </form>
    </body>
</html>
