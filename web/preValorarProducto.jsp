<%-- 
    Document   : preValorarProducto
    Created on : 05-abr-2020, 19:09:11
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

String id = request.getParameter("idProducto");
String titulo = request.getParameter("titulo");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Valore el Producto : <%=titulo%></h3>
        
        <form action="ValorarProducto" method="post">
        Comentario sobre el producto :<br/> <textarea name="comentarioProducto" cols="50" rows="5" requiered ></textarea><br/><br/>
        Puntuación : <input type="number" id="quantity" name="notaProducto" min="1" max="5" /><br/><br/>
        <input type="hidden" name="idProducto" value="<%=id%>">
        <input type="submit" value="Enviar valoracion" />   
        </form>
        
        
    </body>
</html>
