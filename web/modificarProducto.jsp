<%-- 
    Document   : modificarProducto
    Created on : 23-mar-2020, 18:01:55
    Author     : David
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.Categoria"%>
<%@page import="wallapidea.entity.Usuario"%>
<%@page import="wallapidea.entity.Palabraclave"%>
<%@page import="wallapidea.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// hay que enviar el producto por Attribute 
Usuario u = (Usuario)session.getAttribute("usuario");
Producto producto = (Producto)request.getAttribute("producto");
String palabrasclavesstring="";

    List<Categoria> lista = new LinkedList<Categoria>();
    lista= (LinkedList<Categoria>)request.getAttribute("listaCat");
    
String titulo = new String(producto.getTitulo().getBytes(),"UTF-8");
String descripcion = new String(producto.getDescripcion().getBytes(),"UTF-8");
String catprod="";
String catpadre="";

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Modificar producto</title>
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
                <div class="topPanelButton" onclick="location.href='EditarPerfil.jsp'">Editar perfil</div>
                <div class="topPanelButton">Añadir un nuevo producto</div>
                <div class="topPanelButton" onclick="location.href='ProductosServlet'">Buscar algún producto</div>
            </form>
        </div>
        <div class="tarjeta section">
        <h3>Modificar Producto: <%=producto.getTitulo() %></h3>
        </div>
            <div>
        <img src="<%=producto.getFoto()%>" alt="<%=producto.getTitulo() %>"></div>
        <form action="ModificarProducto" method="post">
        Categoria:           
        <select name="cat">
                <%
                    for(Categoria cat : lista){ 
                        if(producto.getCatId().getCatId() == cat.getCatId()){catprod="selected";}else{catprod="";}
                        if(cat.getCategoriaPadre()==null){catpadre="style=\"font-weight:bold;\" ";}else{catpadre="";}
                %>
                    <option value="<%=cat.getCatId()%>" <%=catpadre%> <%=catprod%> >  <%=cat.getNombreCategoria()%> </option> 
                     <% } %>
            </select>
        <input type="hidden" name="productoId" value="<%=producto.getProductId()%>" /><br/>
        Titulo Producto : <input type="text" name="tituloProducto" value="<%=titulo%>" /><br/>
        Descripción Producto :<br/> <textarea name="descripcion" ><%=descripcion%></textarea><br/>
        Precio : <input type="text" name="precioProducto" value="<%=producto.getPrecio()%>" /><br/>
        Foto : <input type="text" name="fotoProducto" value="<%=producto.getFoto()%>" /> <br/>
        Palabras Clave actuales :<br/>
        <% for(Palabraclave pc : producto.getPalabraclaveList()){
            String pal= new String(pc.getPalabra().getBytes(),"UTF-8");
            if(palabrasclavesstring==""){
                palabrasclavesstring=pal;
            }else{
                palabrasclavesstring=palabrasclavesstring+","+pal;
            }
        %>
        <input disabled type="text" name="" value="<%=pal%>" /><br/>
        <% } %>
        
        Palabras Clave :<br/> <textarea name="palabrasClaveProducto" rows="5" cols="50"><%=palabrasclavesstring%></textarea><br/>
        <input type="submit" value="Enviar modificación">
        </form>    
    </body>
</html>

