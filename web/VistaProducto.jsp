<%-- 
    Document   : VistaProducto
    Created on : 15-abr-2020, 12:30:12
    Author     : eduge
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="wallapidea.entity.Palabraclave"%>
<%@page import="java.util.ArrayList"%>
<%@page import="wallapidea.entity.Valoracion"%>
<%@page import="wallapidea.entity.Producto"%>
<%@page import="java.util.List"%>
<%@page import="wallapidea.entity.Usuario"%>
<%
    Usuario u = (Usuario)session.getAttribute("usuario");
    
    Producto p = (Producto)request.getAttribute("producto");
    
    List<Valoracion> lista = p.getValoracionList();
    
    int totalValoraciones = lista.size(); 

    String status;
    String casita="";
    status=(String) request.getAttribute("status");
    System.out.println(status);
    if(status==null){
    status="";
    }
    if(u.getIsadmin()){
        casita="PerfilAdministrador.jsp";
    }else{
        casita="PerfilUsuario.jsp";
    }

%>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Viendo <%=p.getTitulo() %></title>
    </head>
    <body>
        <div class="header">
            <form name="requestForm" method="post">
                <div class="tarjeta button cerrarSesion" onclick="location.href='CerrarSesionServlet'">Cerrar sesión</div>
            </form>
            <img src="img/home.svg" onclick="location.href='<%=casita%>'">
        </div>
        
        <div class="body">

            <div class="tarjeta productSection">

                <div class="productImage">
                    <img src=<%= p.getFoto() %>>
                </div>

                <div class="datosProducto"> 
                    <h1><%= p.getTitulo() %></h1>
                    <h2>Precio: <%= p.getPrecio() %>€</h2>
                    <%if(p.getValoracionmedia() != null) {
                    DecimalFormat df = new DecimalFormat("0.0");
                    %>
                    <h2>Valoración: <%= df.format(p.getValoracionmedia()) %> de <%=totalValoraciones%> valoraciones</h2>
                    <%} else {%>
                    <h2>No existen valoraciones para este producto</h2>
                    <%}%>
                    
                    <h3><%= p.getDescripcion() %></h3>
                    <h3>Categoría: <%= p.getCatId().getNombreCategoria() %></h3>
                    
                    <%
                        if(u.getIsadmin()){
                    %>
                    
                    <h3>Propietario: <%= u.getNombre() %></h3>
                    <h3>Fecha de publicación: <%= p.getFechayhora() %></h3>
                    <h3> <b>Palabras Claves: </b>  
                        <%
                        for(Palabraclave pc: p.getPalabraclaveList()){
                        %>
                            <%= pc.getPalabra()   %>
                        <%
                        }
                        %>
                    </h3>
                    
                    <%
                        }                        
                    %>
                </div>

            </div>

            <h1>Comentarios</h1>

            <div class="tarjeta comentarioSection">
                <div class="tarjeta escribirComentario">
                    <form id="crearComentario" action="ValorarProducto" method="post">
                        <input type="hidden" name="idProducto" value="<%= p.getProductId() %>">
                        Comentario sobre el producto : <br/> 
                        
                        <%if(u.getUsuarioId() != p.getUsuarioId().getUsuarioId() && !u.getIsadmin()){ %>
                            <textarea name="comentarioProducto" rows="5" cols="50"  required></textarea> <br/>
                            Puntuación : <br/> <input name="notaProducto" id="quantity" type="number" min="1" max="5" value="3" required="">
                            
                            <div class="tarjeta valorar" onclick="document.forms['crearComentario'].submit();">
                            Valorar
                             
                            </div>   
                        <% } %>
                       
                    </form>
                </div>
                
                <%
                    for(Valoracion v: lista){
                %>
                
                <div class="tarjeta comentario">
                    <div class="pSupComentario">
                        <h2><%= v.getNota() %></h2>
                        <h3><%= v.getFechayhora() %></h3>
                    </div>
                    <div class="pInfComentario">
                        <h2><%= v.getComentario() %></h2>
                    </div>
                </div>
                
                <%
                    }
                %>
            </div>

    </div>
        
        
    </body>
</html>
