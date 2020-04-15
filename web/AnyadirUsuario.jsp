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
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        
       <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="location.href='CerrarSesionServlet'">Cerrar sesión</div>
            </form>
           
        </div>
        <div class="centralSection tarjeta">
        <h1><%=status%></h1>
         <form name="anyadirusuario" action="AnyadirUsuario" method="POST">
            Email: <input type="text" name="user" value="" /><br/>
            Contraseña: <input type="password" name="pass" value="" /><br/>
            ¿Es Administrador? : <select name="isAdmin"> 
                 <option value="0">No</option>  
                 <option value="1">S&iacute;</option>
            </select><br/>
            <input type="submit" value="Añadir">
         </form>
        </div>
    </body>
</html>
