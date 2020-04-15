<%-- 
    Document   : VerComentarios
    Created on : 09-abr-2020, 13:18:56
    Author     : David
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="wallapidea.entity.Valoracion"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

List<Valoracion> comentarios = (List<Valoracion>)request.getAttribute("comentarios");
String titulo = request.getParameter("titulo");
Usuario u = (Usuario)session.getAttribute("usuario");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Comentarios</title>
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
        <div class="fila">
            <div class="tarjeta section">
        <h3>Comentarios del producto : <%=titulo%> </h3>
            </div>
        <% for(Valoracion v : comentarios) { %>
        <div class="producto">
        
            <h4>Comentario : <%=v.getComentario()%>  </h4>
            <h4>Nota : <%=v.getNota()%> </h4>
            <h4>Fecha :<%= v.getFechayhora()%></h4>
        
        </div>
        <%  } %>
        </div>
    </body>
</html>
