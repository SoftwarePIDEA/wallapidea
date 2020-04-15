<%-- 
    Document   : InicioSesion
    Created on : 24-mar-2020, 11:09:37
    Author     : Pablo
--%>
<%
    String status;
    status=(String) request.getAttribute("status");
    if(status==null){
    status="";
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Inicio de sesión</title>
    </head>
    <body>
        <div class="header">
            &nbsp;
        </div>
        
        <div class="centralSection tarjeta">
            <h1>Inicio de sesión de usuarios WALLAPIDEA</h1>
            <h3><%=status%></h3>
            <form id="login" action="InicioSesionServlet" method="post">
                Usuario:<br/>
                <input type="text" name="user" value="" /><br/>
                Contraseña: <br/>
                <input type="password" name="pass" value="" /><br/>
                <div id="loginButton" class="tarjeta button" onclick="document.forms['login'].submit();">
                    Inicio sesión
                </div>
            </form>
            <h2>    O registrate si no lo estás:</h2>
            <div class="tarjeta button" onclick="location.href='Registro.jsp';">
                Registro
            </div>
        </div>
    </body>
</html>
