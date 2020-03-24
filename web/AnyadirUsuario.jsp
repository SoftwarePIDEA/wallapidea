<%-- 
    Document   : AnyadirUsuario
    Created on : 24-mar-2020, 22:03:48
    Author     : ivanl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
         <form action="AnyadirProducto" method="post">
            Email: <input type="text" name="email" value="" /><br/>
            Contraseña: <input type="text" name="Password" value="" /><br/>
            ¿Es Administrador? : <select name="isAdmin"> 
                 <option value="0">No</option>  
                 <option value="1">S&iacute;</option>
            </select><br/>
            <input type="submit">
         </form>
    </body>
</html>
