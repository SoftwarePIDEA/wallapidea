<%-- 
    Document   : Registro
    Created on : 24-mar-2020, 14:35:41
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String status= (String) request.getAttribute("status");
    if(status==null){
    status="";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Registro de un usuario</title>
    </head>
    <body>
         <div class="header">
            &nbsp;
        </div>      
        <div class="centralSection tarjeta">
            <h1>Introduce tus datos para registrarte</h1>
            <h2>Esta a un paso de ser usuario de wallaPIDEA</h2>
            <h3><%=status%></h3>
            <form id="registro" action="RegistroServlet" method="post">
                E-mail:<br/>
                <input type="text" name="user" value="" /><br/>
                Contraseña: <br/>
                <input type="password" name="pass" value="" /><br/>
                <div id="loginButton" class="tarjeta button" onclick="document.forms['registro'].submit();">
                    Registrarse
                </div>
            </form>
            <h2>O inicia sesión si ya estás registrado</h2>
            <div class="tarjeta button" onclick="location.href='InicioSesion.jsp';">
                Iniciar Sesion
            </div>
        </div>
    </body>
</html>
