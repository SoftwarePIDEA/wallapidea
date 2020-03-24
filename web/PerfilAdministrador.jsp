<%-- 
    Document   : PerfilAdministrador
    Created on : 24-mar-2020, 15:44:32
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    
    
%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><h1>Bienvenido Administrador <%= u.getNombre()%></h1></h1>
    </body>
</html>
