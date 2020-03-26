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
        </script>
        
    </head>
    <body>
        <div class="header">
            &nbsp;
        </div>
        
        <div class="tarjeta sidepanel">
            <h1>Bienvenido, <%= u.getNombre() %></h1>
            
        <form name="requestForm" method="post">
            <div class="tarjeta button">Cerrar sesión</div>
    
            <div class="separator">&nbsp;</div>
    
            <div class="sidepanelButton">Editar perfil</div>
            <div class="sidepanelButton">Añadir un nuevo producto</div>
            
            
                <div class="sidepanelButton" onclick="llamarServlet('BuscarProducto')">Buscar Productos</div>
        
        </form>
        </div>
            
        <div class="tarjeta section">
            <h3>Productos que tienes a la venta: </h3>
            
            <div class="fila">
                
                <%
                    for(Producto p: lista){
                %>
                <div class="producto">
                    <img src=<%= p.getFoto()%>>
                    <h1><%= p.getTitulo()%></h1>
                    <h2><%= p.getPrecio()%> €</h2>
                </div>
                
                <%
                    }
                %>
            </div>
        </div>

    </body>
</html>
