<%-- 
    Document   : check
    Created on : 22/03/2017, 14:54:07
    Author     : 15245533
--%>
<%@page import="crud.HttpExemplo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        HttpExemplo http = new HttpExemplo();
        String login = request.getParameter("uname");
        String senha = request.getParameter("psw");
        String url = "http://localhost:8080/makeband/webresources/makeband/Usuario/get/"+login+"/"+senha;
        String json = http.sendGet(url, "GET");
        
        if (json!=null){
            //response.sendRedirect("restrito.jsp");
           out.println("logou");
        }
        else{
            //response.sendRedirect("negado.jsp");
            out.println("Acesso Negado, tente novamente.<a href=\"index.html\">Clique aqui para o inicio</a>");
        }
        %>
    </body>
</html>
