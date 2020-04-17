/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
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
@WebServlet(name = "preVerComentario", urlPatterns = {"/preVerComentario"})
public class preVerComentario extends HttpServlet {

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private ValoracionFacade valoracionFacade;

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
        // obtengo el producto y luego sus valoraciones 
        String id = request.getParameter("idProducto");
        String titulo = request.getParameter("titulo");
        Producto p = productoFacade.find(Integer.parseInt(id));
        List<Valoracion> comentarios = (List<Valoracion>) valoracionFacade.findByProductId(p);

        // añadimos el atributo comentarios para mostrarlos a continuacion en una jsp 
        request.setAttribute("comentarios", comentarios);

        // damos el control a la jsp que muestra los comentarios
        RequestDispatcher rd = request.getRequestDispatcher("VerComentarios.jsp");
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
