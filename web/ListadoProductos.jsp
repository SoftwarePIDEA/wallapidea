<%-- 
    Document   : ListadoProductos
    Created on : 01-abr-2020, 12:58:21
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    Usuario u = (Usuario)session.getAttribute("usuario");
    List<Producto> lista = (List) request.getAttribute("productos");
    

 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css">
        
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
            <h1>Bienvenido, <%= u.getNombre() %></h1>
        </div>
        
        <div class="topPanel">
            <form name="requestForm" method="post">
                <div class="topPanelButton">Buscar producto</div>
                <div class="topPanelButton" onclick="location.href='AnyadirUsuario.jsp'">Añadir un nuevo Usuario</div>
                <div class="topPanelButton" onclick="location.href='PruebaServlet'">Buscar usuario</div>
            </form>
        </div>
        
        <div class="body">
            
             <form action="AdministradorProductosServlet" method="post">
            Buscar Producto: <input type="text" name="busqueda"/>
            <input type="submit" value="Buscar"/>
        </form>
            <div class="tarjeta section">
                <div id="borrarProductos" class="tarjeta button" onclick="activarEliminar()">Borrar o editar algún producto</div>
                <div class="fila">
                    <h3>Todos los Productos: </h3>
                </div>
                <div class="fila">
                    <%
                        for(Producto p: lista){
                    %>
                    
                    <div class="producto">
                     
                        <img src=<%= p.getFoto()%>>
                        <h1><%= p.getTitulo()%></h1>
                        <h1> Valoración: <%= p.getValoracionmedia()    %></h1>
                        <h1> Propietario <%= p.getUsuarioId().getNombre()    %></h1>
                        <h1>Descripción: </br> <%= p.getDescripcion()    %></h1>
                        <h1>Fecha: </br> <%= p.getFechayhora()    %></h1>
                        <h2><%= p.getPrecio()%> €</h2>
                        <div  class="tarjeta button editar" onclick="location.href='preModiProducto?idProducto=<%=p.getProductId()%>'">Editar</div>
                        <div  class="tarjeta button eliminar" onclick="location.href='EliminarProducto?idProducto=<%=p.getProductId()%>'">Eliminar</div>
                        
                    </div>
                    
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
