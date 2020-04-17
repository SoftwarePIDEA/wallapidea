<%-- 
    Document   : PanelProductos
    Created on : 23-mar-2020, 18:15:13
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Categoria"%>
<%@page import="wallapidea.entity.Palabraclave"%>
<%@page import="wallapidea.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Producto> listaProductos = (List) request.getAttribute("productos");
    String catpadre = "";
    List<Categoria> categorias = (List) request.getAttribute("categorias");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Productos</title>

        <script>
            function showOrHide(selectorModo) {
                var s = document.getElementById("categoria");
                var c = document.getElementById("calendario");

                if (selectorModo.value === "Categoria") {
                    s.style.display = "inline";

                } else if (selectorModo.value === "Fecha") {
                    c.style.display = "inline";
                } else {
                    s.style.display = "none";
                    c.style.display = "none";
                }
            }
        </script>

    </head>
    <body>
        <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="location.href = 'CerrarSesionServlet'">Cerrar sesión</div>
            </form>
        </div>
        <form action="ProductosServlet" method="post">
            Buscar Producto: <input type="text" name="busqueda"/>
            <select name="modoBusqueda" onchange="showOrHide(this)">Modo de busqueda
                <option value="Todos">Todos</option>
                <option value="Recientes">Recientes</option>
                <option value="TituloDescripcion">Titulo y Descripcion</option>
                <option value="Categoria">Categoria</option>
                <option value="PalabrasClave">Palabras Clave</option>
                <option value="Fecha">Fecha</option>    
            </select>

            <select name="Categoria" id="categoria" style="display:none">
                <%
                    for (Categoria cat : categorias) {
                        if (cat.getCategoriaPadre() == null) {
                            catpadre = "style=\"font-weight:bold;\" ";
                        } else {
                            catpadre = "";
                        }
                %>
                <option value="<%=cat.getCatId()%>" <%=catpadre%>  >  <%=cat.getNombreCategoria()%> </option> 
                <% } %>
            </select>

            <input type="date" name="Calendario" id="calendario" style="display:none"/>

            <input type="submit" value="Buscar"/>
        </form>

        <div class="fila">
            <%
                for (Producto p : listaProductos) {
            %>

            <div class="producto">

                <img src=<%= p.getFoto()%>>
                <h1><b><%= p.getTitulo()%></b></h1>
                <h4> <b>Propietario: </b><%= p.getUsuarioId().getNombre()%></h4>
                <h4> <b>Descripción: </b></br> <%= p.getDescripcion()%></h4>
                <h4><b> Fecha: </b></br> <%= p.getFechayhora()%></h4>
                <h4> <b>Valoración:</b> <%= p.getValoracionmedia()%></h4>
                <h4> <b>Categoría:</b> <%= p.getCatId().getNombreCategoria()%></h4>
                <h4> <b>Palabras Claves: </b>  
                    <%
                        for (Palabraclave pc : p.getPalabraclaveList()) {
                    %>
                    <%= pc.getPalabra()%>
                    <% }%>
                </h4>
                <h2><%= p.getPrecio()%> €</h2>
                <div class="tarjeta button comentar" onclick="location.href = 'preVerComentario?idProducto=<%=p.getProductId()%>&titulo=<%=p.getTitulo()%>'">Ver Comentarios</div>
                <div class="tarjeta button valorar" onclick="location.href = 'preValorarProducto.jsp?idProducto=<%=p.getProductId()%>&titulo=<%=p.getTitulo()%>'">Valorar</div>
            </div>

            <%
                }
            %>
        </div>
    </body>
</html>
