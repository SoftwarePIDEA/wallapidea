<%-- 
    Document   : Registro
    Created on : 24-mar-2020, 14:35:41
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String status= (String) request.getAttribute("status");
    if(status==null){
    status="Esta a un paso de ser usuario de wallaPIDEA";
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
        <h1>Introduce tus datos para registrarte</h1>
        <h3><%=status%></h3>
        <form name="registro" action="RegistroServlet" method="POST">
            E-mail:
            <input type="text" name="user" value="" /><br/>
            Contraseña: 
            <input type="password" name="pass" value="" /><br/>
            <input type="submit" value="Registrarse" />
        </form>
        <a href="InicioSesion.jsp">Iniciar Sesion</a>
    </body>
</html>
