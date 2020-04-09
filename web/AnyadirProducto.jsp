<%-- 
    Document   : AnyadirProducto
    Created on : 09-abr-2020, 18:07:58
    Author     : Pablo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir producto</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <div class="header">
            &nbsp;
        </div>
        <div class="centralSection tarjeta">
        <h1>Ponga su producto en venta</h1>
         <form name="anyadirproducto" action="AnyadirProducto" method="POST">
            Titulo Producto: <input type="text" name="titulo" value="" /><br/>
            Descripción: <br/> <textarea name="descripcion" ></textarea><br/>
            Precio: <input type="number" name="precio" value="" /><br/>
            Palabras clave:<br/> <textarea name="palabrasclave" ></textarea><br/>
            Foto (URL): <input type="text" name="foto" value="" /><br/>
            Categoría: <input type="text" name="cat" value="" /><br/>
            <input type="submit" value="Añadir">
         </form>
        </div>
    </body>
</html>
