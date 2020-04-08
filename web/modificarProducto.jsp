<%-- 
    Document   : modificarProducto
    Created on : 23-mar-2020, 18:01:55
    Author     : David
--%>

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

String titulo = new String(producto.getTitulo().getBytes(),"UTF-8");
String descripcion = new String(producto.getDescripcion().getBytes(),"UTF-8");

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
        <h3>Modificar Producto</h3>
        </div>
        <form action="ModificarProducto" method="post">
        Nombre de la categoria :</br><select name="idCategoria"  size="4" required>
                                <optgroup label="Informática">
                                  <option value="0">Sin subcategoria</option>  
                                  <option value="1"  >Portátiles</option>
                                  <option value="2"  >Tablet´s</option>
                                  <option value="3"  >Pc´s</option>
                                </optgroup>
                                <optgroup label="Telefonía">
                                  <option value="4" >Sin subcategoria</option>
                                  <option value="5" >Iphone</option>
                                  <option value="6" >Android</option>
                                  <option value="7" >Windows phone</option>
                                </optgroup>
                                <optgroup label="TV">
                                  <option value="8" >Sin subcategoria</option>
                                  <option value="9" >Led´s</option>
                                  <option value="10" >LCD</option>
                                  <option value="11" >Plasmas</option>
                                </optgroup>
                              </select><br/>
        <input type="hidden" name="productoId" value="<%=producto.getProductId()%>" /><br/>
        Titulo Producto : <input type="text" name="tituloProducto" value="<%=titulo%>" /><br/>
        Descripción Producto :<br/> <textarea name="descripcion" ><%=descripcion%></textarea><br/>
        Precio : <input type="text" name="precioProducto" value="<%=producto.getPrecio()%>" /><br/>
        Foto : <input type="file" name="fotoProducto" placeholder="Description" value="<%=producto.getFoto()%>" /> <br/>
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

