<%-- 
    Document   : PerfilAdministrador
    Created on : 24-mar-2020, 15:44:32
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    System.out.println("Ha entrado en el PANEL ADMIN: "+u.getNombre());
    List<Usuario> lista = (List) request.getAttribute("listaUsuarios");
    
%>  
<!DOCTYPE html>
<html>
    
 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Panel ADMIN</title>
        
         <script>
            function llamarServlet(direccion)
            {
                document.requestForm.action = direccion;
                document.requestForm.submit();
            }
            
            function activarEliminar(){
                var elements = document.querySelectorAll('.eliminar,.editar');
                for(var i=0; i<elements.length; i++){
                    if (elements[i].style.display == "block"){
                        elements[i].style.display = "none";
                    }else{
                        elements[i].style.display = "block";
                    }
                }
            }
            
            
        </script>
    </head>
    
    <body>
       <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="llamarServlet('CerrarSesionServlet')">Cerrar sesión</div>
            </form>
            <h1>Bienvenido, <%= u.getNombre() %></h1>
        </div>
        
        <div class="topPanel">
            <form name="requestForm" method="post">
                <div class="topPanelButton">Editar perfil</div>
                <div class="topPanelButton" onclick="llamarServlet('AnyadirUsuario')">Añadir un nuevo Usuario</div>
                <div class="topPanelButton" onclick="llamarServlet('ProductosServlet')">Buscar algún usuario</div>
            </form>
        </div>
        
      
       <div class="body">
           <div class="tarjeta section">
               <div id="borrarProductos" class="tarjeta button" onclick="activarEliminar()">Borrar o editar algún Usuario</div>
            <div class="fila">
                    <h3>Usuarios: </h3>
                </div>
            <div class="fila">
                <% for(Usuario us : lista) { %>
                <div class="producto">
                    
                    <b> ID:</b> <%=us.getUsuarioId()%></BR>
                    <b> USUARIO: </b> <%=us.getNombre()%></BR>
                    <b>  ADMIN: </b> <%=us.getIsadmin()%></BR>
                    <%if(!us.getIsadmin()){%><b> CONTRASEÑA: </b> <%= us.getPass()  %></BR> <% } %>
                    <div  class="tarjeta button editar" >Editar</div>
                    <div  class="tarjeta button eliminar" onclick="location.href='EliminarUsuario?id=<%=us.getUsuarioId()%>'">Eliminar</div>
                </div>
               <% } %>               
           </div>
        </div>
       </div>
    </body>
</html>
