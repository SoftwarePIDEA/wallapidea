<%-- 
    Document   : AnyadirProducto
    Created on : 09-abr-2020, 18:07:58
    Author     : Pablo
--%>
<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.LinkedList"%>
<%@page import="wallapidea.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="wallapidea.dao.CategoriaFacade"%>
<%
    List<Categoria> lista = new LinkedList<Categoria>();
    lista= (LinkedList<Categoria>)request.getAttribute("listaCat");
    String catpadre="";
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir producto</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="location.href = 'CerrarSesionServlet'">Cerrar sesión</div>
                <img src="img/home.svg" onclick="location.href='PerfilUsuario.jsp'">
            </form>
        </div>
        <div class="centralSection tarjeta">
        <h1>Ponga su producto en venta</h1>
         <form name="anyadirproducto" action="AnyadirProducto" method="POST">
            Titulo Producto: <input type="text" name="titulo" value="" /><br/>
            Descripción: <br/> <textarea name="descripcion" ></textarea><br/>
            Precio: <input type="number" name="precio" value="" /><br/>
            Palabras clave:<br/> <textarea name="palabrasclave" ></textarea><br/>
            Foto (URL): <input type="text" name="foto" value="" /><br/>
            Categoría: 
            <select name="cat">
                <%
                    for(Categoria cat : lista){ 
                if(cat.getCategoriaPadre()==null){catpadre="style=\"font-weight:bold;\" ";}else{catpadre="";}
                %>
            <option value="<%=cat.getCatId()%>" <%=catpadre%>><%=cat.getNombreCategoria()%></option> 
                     <% } %>
            </select>
            <input type="submit" value="Añadir">
         </form>
        </div>
    </body>
</html>
