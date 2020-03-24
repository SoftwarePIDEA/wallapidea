<%-- 
    Document   : PerfilUsuario
    Created on : 24-mar-2020, 13:23:23
    Author     : eduge
--%>

<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.*"%>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    
    List<Producto> lista = u.getProductoList();
%>    

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido <%= u.getNombre()%></h1>
        
        <% 
            for(Producto p: lista){
                
            

        %>
        <h2><%=p.toString()%></h2>
        
        <%
            }
        %>
    </body>
</html>
