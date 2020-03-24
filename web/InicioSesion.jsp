<%-- 
    Document   : InicioSesion
    Created on : 24-mar-2020, 11:09:37
    Author     : Pablo
--%>
<%
    String status;
    status=(String) request.getAttribute("status");
    if(status==null){
    status="Introduzca sus datos";
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesión</title>
    </head>
    <body>
        <h1>Inicio de sesión de usuarios WALLAPIDEA</h1>
        <h3><%=status%></h3>
        <form action="InicioSesionServlet" method="post">
            Usuario:
            <input type="text" name="user" value="" /><br/>
            Contraseña: 
            <input type="password" name="pass" value="" /><br/>
            <input type="submit" value="Iniciar sesion" />
        </form>
        <a href="Registro.jsp">Registro</a>
    </body>
</html>
