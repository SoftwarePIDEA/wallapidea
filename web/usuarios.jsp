<%-- 
    Document   : usuarios
    Created on : 23-mar-2020, 12:28:32
    Author     : David
--%>

<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
  List <Usuario> usuarios = (List)request.getAttribute("usuarios");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Admin </th>
                </tr>
            </thead>
            <tbody>
                <%for(Usuario u : usuarios) { %>
                <tr>
                    <td><%=u.getNombre()%></td>
                    <td><%=u.getIsadmin()%></td>
                </tr>
               <% } %>
            </tbody>
        </table>

        
        
    </body>
</html>
