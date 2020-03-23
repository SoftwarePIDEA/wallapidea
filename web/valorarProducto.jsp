<%-- 
    Document   : valorarProducto
    Created on : 23-mar-2020, 16:45:51
    Author     : David
--%>

<%@page import="wallapidea.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
   //  pasar producto como atributo o su id 
   String id = (String)request.getAttribute("id");
   //Producto producto = (Producto)request.getAttribute("producto");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Valorar producto</title>
    </head>
    <body>
        <form action="ValorarProducto" method="post">
        <h1>Valorar Producto</h1>
        Nota del producto : <input type="text" name="notaProducto" required /><br/>
        Comentario del producto : <textarea name ="comentarioProducto" row="5" cols="30" required>
        Escribir comentario del producto aquí
        </textarea><br/>
        <input type="hidden" name="idProducto" value="<%=id%>"/>
        <input type="submit" value="Enviar valoración">
        </form>
        
    </body>
</html>
