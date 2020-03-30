<%-- 
    Document   : modificarProducto
    Created on : 23-mar-2020, 18:01:55
    Author     : David
--%>

<%@page import="wallapidea.entity.Palabraclave"%>
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
        Nombre de la categoria :</br><select name="idCategoria"  size="4" required>
                                <optgroup label="Electrodomesticos">
                                  <option value="0">Sin subcategoria</option>  
                                  <option value="1"  >Elec 1</option>
                                  <option value="2"  >Elec 2</option>
                                  <option value="3"  >Elec 3</option>
                                </optgroup>
                                <optgroup label="Video">
                                  <option value="4" >Sin subcategoria</option>
                                  <option value="5" >Video 1</option>
                                  <option value="6" >Video 2</option>
                                  <option value="7" >Video 3</option>
                                </optgroup>
                              </select><br/>
        <input type="hidden" name="productoId" value="<%=producto.getProductId()%>" /><br/>
        Titulo Producto : <input type="text" name="tituloProducto" value="<%=producto.getTitulo()%>" /><br/>
        Descripción Producto :<br/> <textarea name="descripcion" ><%=producto.getDescripcion()%></textarea><br/>
        Precio : <input type="text" name="precioProducto" value="<%=producto.getPrecio()%>" /><br/>
        Foto : <input type="file" name="fotoProducto" placeholder="Description" value="<%=producto.getFoto()%>" /> <br/>
        Palabras Clave actuales :<br/>
        <% for(Palabraclave pc : producto.getPalabraclaveList()){%>
        <input type="text" name="" value="<%=pc.getPalabra()%>" /><br/>
        <% } %>
        
        Palabras Clave :<br/> <textarea name="palabrasClaveProducto" rows="5" cols="50">
        Introduzca las Palabras Clave separadas por espacio 
        </textarea><br/>
        <input type="submit" value="Enviar modificación">
        </form>    
    </body>
</html>

