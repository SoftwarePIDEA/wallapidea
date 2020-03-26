<%-- 
    Document   : AnyadirUsuario
    Created on : 24-mar-2020, 22:03:48
    Author     : ivanl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String status= (String) request.getAttribute("status");
    if(status==null){
    status="Añadir un usuario";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN: Añadir usuario</title>
    </head>
    <body>
        <h3><%=status%></h3>
         <form name="anyadirusuario" action="AnyadirUsuario" method="POST">
            Email: <input type="text" name="user" value="" /><br/>
            Contraseña: <input type="password" name="pass" value="" /><br/>
            ¿Es Administrador? : <select name="isAdmin"> 
                 <option value="0">No</option>  
                 <option value="1">S&iacute;</option>
            </select><br/>
            <input type="submit" value="Añadir">
         </form>
    </body>
</html>
