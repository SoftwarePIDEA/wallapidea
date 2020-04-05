<%-- 
    Document   : preValorarProducto
    Created on : 05-abr-2020, 19:09:11
    Author     : David
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
Usuario u = (Usuario)session.getAttribute("usuario");
String id = request.getParameter("idProducto");
String titulo = request.getParameter("titulo");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Valorar Producto</title>
    </head>
    <body>
          <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="location.href='CerrarSesionServlet'">Cerrar sesión</div>
            </form>
            <h1>Bienvenido, <%= u.getNombre() %></h1>
        </div>
        
        <div class="topPanel">
            <form name="requestForm" method="post">
                <div class="topPanelButton" onclick="location.href='EditarPerfil.jsp'">Editar perfil</div>
                <div class="topPanelButton">Añadir un nuevo producto</div>
                <div class="topPanelButton" onclick="location.href='ProductosServlet'">Buscar algún producto</div>
            </form>
        </div>
        
        <div class="tarjeta section">
        <h3>Valore el Producto : <%=titulo%></h3>
        </div>
        
        <form action="ValorarProducto" method="post">
        Comentario sobre el producto :<br/> <textarea name="comentarioProducto" cols="50" rows="5" requiered ></textarea><br/><br/>
        Puntuación : <input type="number" id="quantity" name="notaProducto" min="1" max="5" /><br/><br/>
        <input type="hidden" name="idProducto" value="<%=id%>">
        <input type="submit" value="Enviar valoracion" />   
        </form>
        
        
    </body>
</html>
