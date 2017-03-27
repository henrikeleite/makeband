<%-- 
    Document   : pesquisa
    Created on : 24/03/2017, 16:26:58
    Author     : 15245533
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        request.setCharacterEncoding("UTF-8");
        String pesquisa = request.getParameter("pesquisa");
        String filtro = request.getParameter("gender");
        if(filtro==null){
            
        }
        %>
    </body>
</html>
