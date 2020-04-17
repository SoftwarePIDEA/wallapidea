<%-- 
    Document   : PerfilAdministrador
    Created on : 24-mar-2020, 15:44:32
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    RequestDispatcher rd;
    Usuario u = (Usuario)session.getAttribute("usuario");
    //
    List<Usuario> lista = null;
    if(u==null || !u.getIsadmin()){
        rd = request.getRequestDispatcher("InicioSesion.jsp");
        rd.forward(request, response);
    }else{
        lista=(List) request.getAttribute("listaUsuarios");
        System.out.println("Ha entrado en el PANEL ADMIN: "+u.getNombre());
    }
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
                <div class="tarjeta button cerrarSesion" onclick="location.href='CerrarSesionServlet'">Cerrar sesión</div>
            </form>
            <h1>Bienvenido Administrador, <%= u.getNombre() %></h1>
        </div>
        
        <div class="topPanel">
            <form name="requestForm" method="post">
                <div class="topPanelButton" onclick="location.href='ProductosServlet'">Buscar producto</div>
                <div class="topPanelButton" onclick="location.href='AnyadirUsuario.jsp'">Añadir un nuevo Usuario</div>
                <div class="topPanelButton" >Buscar usuario</div>
            </form>
        </div>
        
      
       <div class="body">
           
           <div class="tarjeta section">
             <form action="UsuariosServlet" method="post">
            <h3>Buscar Usuario: <input type="text" name="busqueda"/>
            <input type="submit" value="Buscar"/>
            </form>
            </div>
           
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
                    <%if(!us.getIsadmin()){%><b> CONTRASEÑA: </b> <%= us.getPass()  %></BR> 
                    <div  class="tarjeta button editar" onclick="location.href='preModiUsuario?id=<%=us.getUsuarioId()%>'">Editar</div>
                    <div  class="tarjeta button eliminar" onclick="location.href='EliminarUsuario?id=<%=us.getUsuarioId()%>'">Eliminar</div>
                <% } %></div>
               <% } %>               
           </div>
        </div>
       </div>
    </body>
</html>
