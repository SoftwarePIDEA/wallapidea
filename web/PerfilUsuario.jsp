<%-- 
    Document   : PerfilUsuario
    Created on : 24-mar-2020, 13:23:23
    Author     : eduge
--%>

<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.*"%>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    
    List<Producto> lista = u.getProductoList();
%>    

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
        
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
                <div class="topPanelButton">Añadir un nuevo producto</div>
                <div class="topPanelButton" onclick="llamarServlet('ProductosServlet')">Buscar algún producto</div>
            </form>
        </div>
            
        <div class="body">
            <div class="tarjeta section">
                <div id="borrarProductos" class="tarjeta button" onclick="activarEliminar()">Borrar o editar algún producto</div>
                <div class="fila">
                    <h3>Productos que tienes a la venta: </h3>
                </div>
                <div class="fila">
                    <%
                        for(Producto p: lista){
                    %>
                    
                    <div class="producto">
                        
                     
                        <img src=<%= p.getFoto()%>>
                        <h1><%= p.getTitulo()%></h1>
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
