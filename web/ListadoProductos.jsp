<%-- 
    Document   : ListadoProductos
    Created on : 01-abr-2020, 12:58:21
    Author     : ivanl
--%>

<%@page import="wallapidea.entity.Categoria"%>
<%@page import="wallapidea.entity.Palabraclave"%>
<%@page import="wallapidea.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    Usuario u = (Usuario)session.getAttribute("usuario");
    List<Producto> lista = (List) request.getAttribute("productos");
    String catpadre="";
    List<Categoria> categorias = (List) request.getAttribute("categorias");

 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN: Buscar producto</title>
        <link rel="stylesheet" href="css/style.css">
        
        <script>
            
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
                <div class="tarjeta button cerrarSesion" onclick="location.href='CerrarSesionServlet'">Cerrar sesión</div>
            </form>
             <img src="img/home.svg" onclick="location.href='PerfilAdministrador.jsp'">
            <h1>Bienvenido Administrador, <%= u.getNombre() %></h1>
        </div>
        
        <div class="topPanel">
            <form name="requestForm" method="post">
                <div class="topPanelButton">Buscar producto</div>
                <div class="topPanelButton" onclick="location.href='AnyadirUsuario.jsp'" >Añadir un nuevo usuario</div>
                <div class="topPanelButton" onclick="location.href='UsuariosServlet'" >Buscar usuario</div>
            </form>
        </div>
        
        <div class="body">
            
            <div class="tarjeta section">
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
            </div>
            <div class="tarjeta section2">
                <div id="borrarProductos" class="tarjeta button" onclick="activarEliminar()">Borrar o editar algún producto</div>
                <div class="fila">
                    <h3>Todos los Productos: </h3>
                </div>
                <div class="fila">
                    <%
                        for(Producto p: lista){
                    %>
                    
                    <div class="producto" onclick="location.href='MostrarProducto?idProducto=<%=p.getProductId()%>'">
                     
                        <img src=<%= p.getFoto()%>>
                        <h1><%= p.getTitulo()%></h1>
                        <h2><%= p.getPrecio()%> €</h2>
                        <div  class="tarjeta button editar" onclick="location.href='preModiProducto?idProducto=<%=p.getProductId()%>'; e = window.event; e.cancelBubble = true; e.stopPropagation();">Editar</div>
                        <div  class="tarjeta button eliminar" onclick="location.href='EliminarProducto?idProducto=<%=p.getProductId()%>'; e = window.event; e.cancelBubble = true; e.stopPropagation();">Eliminar</div>
                        
                    </div>
                    
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
