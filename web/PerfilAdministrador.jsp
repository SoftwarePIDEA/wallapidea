<%-- 
    Document   : PerfilAdministrador
    Created on : 24-mar-2020, 15:44:32
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    System.out.println("Ha entrado en el PANEL ADMIN: "+u.getNombre());
    List<Usuario> lista = (List) request.getAttribute("listaUsuarios");
    
%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Panel ADMIN</title>
    </head>
    <body>
       
        
        <div class="header">
            &nbsp;
        </div>
        
        <div class="tarjeta sidepanel">
            <h1>Bienvenido, <%= u.getNombre() %></h1>
    
            <div class="tarjeta button">Cerrar sesión</div>
    
            <div class="separator">&nbsp;</div>
    
            <div class="sidepanelButton">Editar perfil</div>
            <div class="sidepanelButton">Añadir un nuevo producto</div>
            <div class="sidepanelButton">Buscar algún producto</div>
            <div class="sidepanelButton"><a href="AnyadirUsuario.jsp">Añadir Usuario</a></div>
        </div>
            
        <div class="tarjeta section">
            Usuarios:
                <%for(Usuario us : lista) { %>
                <div class="producto">
                    
                    <b>ID:</b> <%=us.getUsuarioId()%></BR>
                    <b> USUARIO: </b> <%=us.getNombre()%></BR>
                    <b>  ADMIN: </b> <%=us.getIsadmin()%></BR>
                    <%if(!us.getIsadmin()){%><b> CONTRASEÑA: </b> <%= us.getPass()  %></BR>
                    <a href="EliminarUsuario?id=<%= us.getUsuarioId() %>"> Eliminar</a><%}%>
                </div>
               <% } %>
               
          
        </div>
            
    </body>
</html>
