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
    List<Usuario> lista = (List) request.getAttribute("listaUsuarios");
    
%>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido Administrador <%= u.getNombre()%></h1>
        
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NOMBRE </th>
                    <th>¿Es Administrador? </th>
                    <th>Contraseña</th>
                    <th> </th>

                </tr>
            </thead>
            <tbody>
                <%for(Usuario us : lista) { %>
                <tr>
                    
                    <td><%=us.getUsuarioId()%></td>
                    <td><%=us.getNombre()%></td>
                    <td><%=us.getIsadmin()%></td>
                    <td><%= us.getPass()  %></td>
                    <td> <a href="EliminarUsuario?id=<%= us.getUsuarioId() %>"> Eliminar</a></td>
                </tr>
               <% } %>
            </tbody>
        </table>
            
            <a href="AnyadirUsuario.jsp">Añadir Usuario</a>
    </body>
</html>
