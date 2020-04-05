<%-- 
    Document   : PanelProductos
    Created on : 23-mar-2020, 18:15:13
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  List <Producto> Producto = (List)request.getAttribute("productos");
  
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ProductosServlet" method="post">
            Buscar Producto: <input type="text" name="busqueda"/>
            <input type="submit" value="Buscar"/>
        </form>
        
        
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion </th>
                    <th>Usuario </th>
                    <th>Hacer Valoracion</th>
                </tr>
            </thead>
            <tbody>
                <%for(Producto p : Producto) { %>
                <tr>
                    <td><%= p.getTitulo()%></td>
                    <td><%= p.getDescripcion() %></td>
                    <td><%= p.getUsuarioId() %></td>
                    <td><div  class="tarjeta button editar" onclick="location.href='preValorarProducto.jsp?idProducto=<%=p.getProductId()%>&titulo=<%=p.getTitulo()%>'">Valorar</div></td>
                </tr>
               <% } %>
            </tbody>
        </table>
    </body>
</html>
