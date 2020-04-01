<%-- 
    Document   : modificarUsuario
    Created on : 01-abr-2020, 12:03:02
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%
// hay que enviar el producto por Attribute 

Usuario usuario = (Usuario)request.getAttribute("usuario");


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            &nbsp;
        </div>
        <div class="centralSection tarjeta">
            <h1>Modificar Usuario</h1>
        <form action="ModificarUsuario" method="post">
         <input type="hidden" name="usuarioId" value="<%= usuario.getUsuarioId()%>" /></br>
            Nombre : <input type="text" name="nombreUsuario" value="<%=usuario.getNombre()%>" /></br>
            Contraseña : <input type="text" name="passUsuario" value="<%= usuario.getPass()%>"></br>
            <input type="submit" value="Editar">
        </form>  
        </div>
    </body>
</html>
