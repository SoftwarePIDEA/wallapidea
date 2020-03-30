/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;
import javax.ejb.EJB;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import wallapidea.dao.CategoriaFacade;
import wallapidea.dao.PalabraclaveFacade;
import wallapidea.dao.ProductoFacade;
import wallapidea.entity.Categoria;
import wallapidea.entity.Palabraclave;

import wallapidea.entity.Producto;
import wallapidea.entity.Usuario;

/**
 *
 * @author David
 */
@WebServlet(name = "ModificarProducto", urlPatterns = {"/ModificarProducto"})
public class ModificarProducto extends HttpServlet {

    @EJB
    private PalabraclaveFacade palabraclaveFacade;

   

    @EJB
    private ProductoFacade productoFacade;


    @EJB
    private CategoriaFacade categoriaFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Usuario u = (Usuario)session.getAttribute("usuario");
        
       
        // obtengo datos nuevos del producto
        String idP = request.getParameter("productoId");
        String categoriaId = request.getParameter("idCategoria");
        String titulo = request.getParameter("tituloProducto");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precioProducto");
        String foto = request.getParameter("fotoProducto");
        String  pCs = request.getParameter("palabrasClaveProducto"); 
        System.out.println(pCs);
        String [] palabrasClave = pCs.split(" ");
     
       
        
        // obtengo el producto por atributos
        Producto producto = productoFacade.find(Integer.parseInt(idP));
        List<Producto>lista=u.getProductoList();
        lista.remove(producto);
        
        // obtengo categoría nueva del producto 
        Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));
        
        System.out.print(categoria.getCatId());
        
        List<Palabraclave> listaClaves = new ArrayList<>() ; 
         //añadimos nuevas palabras clave
        for(String pc : palabrasClave){
               
           if(!palabraclaveFacade.existsPalabra(pc)){
               
               palabraclaveFacade.insertPalabraClave(pc);
             
           } 
            
        }
        
       
        
        //actulizamos p.clave a producto 
        //producto.setPalabraclaveList(producto.getPalabraclaveList());
        
        
        // actualizo valores del producto 
        producto.setCatId(categoria);
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(Double.parseDouble(precio));
        producto.setFoto(foto);
        
        
        productoFacade.updateByProduct(producto.getProductId(),producto.getCatId(),producto.getTitulo(),producto.getDescripcion(),producto.getPrecio(), producto.getFoto(),producto.getValoracionmedia());
     
        lista.add(producto);
        
       
         
        RequestDispatcher rd = request.getRequestDispatcher("PerfilUsuario.jsp");
        rd.forward(request, response);
   
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
