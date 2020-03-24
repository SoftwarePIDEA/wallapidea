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
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Descripcion </th>
                </tr>
            </thead>
            <tbody>
                <%for(Producto p : Producto) { %>
                <tr>
                    <td><%= p.getTitulo()%>td>
                    <td><%= p.getDescripcion() %></td>
                </tr>
               <% } %>
            </tbody>
        </table>
    </body>
</html>
