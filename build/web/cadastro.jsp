<%-- 
    Document   : cadastro
    Created on : 22/03/2017, 15:56:48
    Author     : 15245533
--%>
<%@page import="crud.HttpExemplo"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html">
        <meta charset="utf-8"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%
        request.setCharacterEncoding("UTF-8");
        Usuario user = new Usuario();
        HttpExemplo http = new HttpExemplo();
        user.setNome(request.getParameter("nome"));
        user.setSobrenome(request.getParameter("sobrenome"));
        user.setEmail(request.getParameter("email"));
        user.setSenha(request.getParameter("senha"));
        user.setPais(request.getParameter("pais"));
        user.setEstado(request.getParameter("estado"));
        user.setCidade(request.getParameter("cidade"));
        String dia, mes, ano;
        dia = request.getParameter("dia");
        mes = request.getParameter("mes");
        ano = request.getParameter("ano");
        user.setNascimento(ano+"-"+mes+"-"+dia);
        user.setSexo(request.getParameter("gender"));
        Gson g = new Gson();
        String json = g.toJson(user, Usuario.class);
        //out.println(json);
        //out.println(user.getPais());
        String url = "http://localhost:8080/makeband/webresources/makeband/Usuario/inserir";
        http.sendPost(url, json, "POST");
        %>
    </body>
</html>
