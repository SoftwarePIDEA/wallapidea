<%-- 
    Document   : modificarProducto
    Created on : 23-mar-2020, 18:01:55
    Author     : David
--%>

<%@page import="wallapidea.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// hay que enviar el producto por Attribute 

Producto producto = (Producto)request.getAttribute("producto");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar Producto</h1>
        <form action="ModificarProducto" method="post">
        <h1>Valorar Producto</h1>
        Nombre de la categoria :<select name="idCategoria" multiple size="4">
                                <optgroup label="Electrodomesticos">
                                  <option value="0">Sin subcategoria</option>  
                                  <option value="1">Elec 1</option>
                                  <option value="2">Elec 2</option>
                                  <option value="3" >Elec 3</option>
                                </optgroup>
                                <optgroup label="Video">
                                  <option value="4">Video 1</option>
                                  <option value="5">Video 2</option>
                                  <option value="6">Video 3</option>
                                </optgroup>
                              </select><br/>
        Titulo Producto : <input type="text" name="tituloProducto" value="<%=producto.getTitulo()%>" /><br/>
        Descripción Producto : <textarea name="descripcion" ><%=producto.getDescripcion()%></textarea><br/>
        Precio : <input type="text" name="precioProducto" value="<%=producto.getPrecio()%>" /><br/>
        Foto : <input type="text" name="fotoProducto" value="<%=producto.getFoto()%>" /><br/>
        Palabras Claves : <input type="text" name="palabrasClaveProducto" value="" /><br/>
        <input type="submit" value="Enviar modificación">
        </form>     
    </body>
</html>
