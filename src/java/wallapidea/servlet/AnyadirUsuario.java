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
import wallapidea.dao.UsuarioFacade;
import wallapidea.entity.Usuario;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "AnyadirUsuario", urlPatterns = {"/AnyadirUsuario"})
public class AnyadirUsuario extends HttpServlet {
   @EJB
    private UsuarioFacade usuarioFacade;
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
        RequestDispatcher rd;
        Usuario usuario=null;
        String status;
        //OBTENEMOS LOS PARAMETROS DEL FORM DE AnyadirUsuario.jsp
        String user= request.getParameter("user");
        String pass= request.getParameter("pass");
        String isAdminParameter= request.getParameter("isAdmin");
        Boolean isAdmin;
        if (isAdminParameter.equals("0")){
            isAdmin=false;
        }else{
            isAdmin=true;
        }

        System.out.println("Intentamos Anyadir Usuario: "+user+"-"+pass+"-"+isAdmin);
        if(!usuarioFacade.isNombreRegistered(user)){
            usuario = new Usuario();
            usuario.setNombre(user);
            usuario.setPass(pass);
            usuario.setIsadmin(isAdmin);
            usuarioFacade.create(usuario);
            status = "Usuario registrado correctamente en wallaPIDEA";
            System.out.println(status);
            request.setAttribute("status", status);
            List<Usuario> listaUsuarios=usuarioFacade.findAll();
            request.setAttribute("listaUsuarios", listaUsuarios);
            rd = request.getRequestDispatcher("PerfilAdministrador.jsp");
            rd.forward(request, response);
        }else{
            status = "El usuario ya existe en wallaPIDEA";
            System.out.println(status);
            request.setAttribute("status", status);
            rd = request.getRequestDispatcher("AnyadirUsuario.jsp");
            rd.forward(request, response); 
        }
            
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
