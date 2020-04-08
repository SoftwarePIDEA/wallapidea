<%-- 
    Document   : EditarPerfil
    Created on : 01-abr-2020, 12:10:24
    Author     : eduge
--%>

<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.*"%>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    
    String status = (String)request.getAttribute("status");
    String statusColor = (String)request.getAttribute("statusC");
    String mode = (String)request.getParameter("mode");
    String status0 = "", status1 = "";
    
    if(status == null){
        status = "";
        statusColor = "red";
    }
    
    if(mode == null){
        mode = "";
    }
    
    if(mode.equals("0")){
        status0 = status;
    }else if(mode.equals("1")){
        status1 = status;
    }
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Editar Perfil</title>
    </head>
    <body>
        <div class="header">
            &nbsp;
        </div>
        <div class="centralSection tarjeta">
            <h1>Cambiar el nombre de usuario</h1>
            <form id="changeNombre" action="EditarPerfilServlet" method="post">
                <h3 style="color:<%=statusColor%>"><%=status0%></h3>
                <input type="hidden" name="mode" value="0">
                Nuevo nombre de usuario:<br/>
                <input type="text" name="user" value="<%=u.getNombre()%>" /><br/>
                <div id="loginButton" class="tarjeta button" onclick="document.forms['changeNombre'].submit();">
                    Cambiar nombre
                </div>
            </form>
            <div class="separator">&nbsp;</div>    
            <form id="changePassword" action="EditarPerfilServlet" method="post">
                <h3 style="color:<%=statusColor%>"><%=status1%></h3>
                <input type="hidden" name="mode" value="1">
                Escribe la contraseña antigua:<br/>
                <input type="text" name="passAntigua" /><br/>
                Escribe la contraseña nueva:<br/>
                <input type="text" name="passNueva" /><br/>
                Vuelve a repetir la contraseña:<br/>
                <input type="text" name="passNueva2" /><br/>
                <div id="loginButton" class="tarjeta button" onclick="document.forms['changePassword'].submit();">
                    Cambiar contraseña
                </div>
            </form>
        </div>
    </body>
</html>
