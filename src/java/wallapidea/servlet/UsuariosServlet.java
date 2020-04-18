/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import wallapidea.dao.UsuarioFacade;
import wallapidea.entity.Usuario;
import wallapidea.service.UsuarioService;

/**
 *
 * @author ivanl
 */
@WebServlet(name = "UsuariosServlet", urlPatterns = {"/UsuariosServlet"})
public class UsuariosServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private UsuarioService usuarioService;

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String buscar = request.getParameter("busqueda");

        if (buscar == null || buscar.equals("")) {
            session.setAttribute("listaUsuarios", usuarioFacade.findAll());
        } else {
            List<Usuario> lista = usuarioService.BuscarPorNombreoID(buscar);
            session.setAttribute("listaUsuarios", lista);
        }

        /*
        Usuario us = usuarioFacade.findByNombre(buscar);

        if (us != null) {
            List<Usuario> lista = new LinkedList<>();
            lista.add(us);
            request.setAttribute("listaUsuarios", lista);
        } else {
            List<Usuario> lista = usuarioFacade.findAll();
            request.setAttribute("listaUsuarios", lista);
        }*/
        RequestDispatcher rd = request.getRequestDispatcher("PerfilAdministrador.jsp");
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
