/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wallapidea.dao.ProductoFacade;
import wallapidea.dao.ValoracionFacade;
import wallapidea.entity.Producto;
import wallapidea.entity.Valoracion;

/**
 *
 * @author David
 */
@WebServlet(name = "ValorarProducto", urlPatterns = {"/ValorarProducto"})
public class ValorarProducto extends HttpServlet {

    @EJB
    private ValoracionFacade valoracionFacade;

    @EJB
    private ProductoFacade productoFacade;

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
      
        // hay que pasar por formulario el id del producto , la nota y el comentario 
        String id = request.getParameter("idProducto");
        String nota = request.getParameter("notaProducto");
        String comentario = new String(request.getParameter("comentarioProducto").getBytes(),"UTF-8");
        // obtenemos el producto de la base de datos
        Producto producto = productoFacade.find(Integer.parseInt(id));
        // obtenemos lista de valoraciones de bd y valoracion concreta del producto
        List<Valoracion> valoraciones = valoracionFacade.findAll();
      
        
        double sumaTotal = 0 ; 
        int totalValoraciones = 0 ; 
        // calculamos valoracion media y numero de valoraciones
        for(Valoracion v : valoraciones){
            if(v.getProductId().equals(producto.getProductId())){
                sumaTotal += v.getNota();
                totalValoraciones++;
            }
        }
        double valoracionFinal = sumaTotal + Integer.parseInt(nota) / totalValoraciones + 1 ; 
        /// fijamos la valoracion media nueva al producto en cuestión
        producto.setValoracionmedia(valoracionFinal);
        // creamos nueva valoracion con datos obtenidos por parámetros
        Valoracion valoracion = new Valoracion();
        valoracion.setComentario(comentario);
        valoracion.setNota(Integer.parseInt(nota));
        valoracion.setProductId(producto);
        
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
