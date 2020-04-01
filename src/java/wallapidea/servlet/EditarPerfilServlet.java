/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author eduge
 */
@WebServlet(name = "EditarPerfilServlet", urlPatterns = {"/EditarPerfilServlet"})
public class EditarPerfilServlet extends HttpServlet {

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
        
        RequestDispatcher rd;
        
        int mode = Integer.parseInt(request.getParameter("mode"));
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        String status = "";
        
        switch (mode){
            case 0: 
                String user = request.getParameter("user");
                if(!u.getNombre().equals(user)){
                    
                    if(!usuarioFacade.isNombreRegistered(user)){
                        u.setNombre(user);
                        usuarioFacade.edit(u);
                        status = "Nombre cambiado con éxito";
                        request.setAttribute("status", status);
                        request.setAttribute("statusC", "green");
                        rd = request.getRequestDispatcher("EditarPerfil.jsp");
                        rd.forward(request, response);
                    }else{
                        status = "El nombre que ha introducido ya existe";
                        request.setAttribute("status", status);
                        request.setAttribute("statusC", "red");
                        rd = request.getRequestDispatcher("EditarPerfil.jsp");
                        rd.forward(request, response);
                    }
                    
                }else{
                    status = "El nombre que ha introducido es el mismo";
                    request.setAttribute("status", status);
                    request.setAttribute("statusC", "red");
                    rd = request.getRequestDispatcher("EditarPerfil.jsp");
                    rd.forward(request, response);
                }
                
                break;
            case 1:
                String passA = request.getParameter("passAntigua");
                String passN = request.getParameter("passNueva");
                String passN2 = request.getParameter("passNueva2");
                
                if(!passA.equals(u.getPass())){
                    status = "La contraseña antigua es incorrecta";
                    request.setAttribute("status", status);
                    request.setAttribute("statusC", "red");
                    rd = request.getRequestDispatcher("EditarPerfil.jsp");
                    rd.forward(request, response);
                }else{
                    
                    if(!passN.equals(passN2)){
                        status = "Las contraseñas nuevas no coinciden";
                        request.setAttribute("status", status);
                        request.setAttribute("statusC", "red");
                        rd = request.getRequestDispatcher("EditarPerfil.jsp");
                        rd.forward(request, response);
                    }else if(passN.equals(passA)){
                        status = "La contraseña nueva debe de ser diferente de la antigua";
                        request.setAttribute("status", status);
                        request.setAttribute("statusC", "red");
                        rd = request.getRequestDispatcher("EditarPerfil.jsp");
                        rd.forward(request, response);
                    }else{
                        u.setPass(passN);
                        usuarioFacade.edit(u);  
                        status = "Contraseña cambiada con éxito";
                        request.setAttribute("status", status);
                        request.setAttribute("statusC", "green");
                        rd = request.getRequestDispatcher("EditarPerfil.jsp");
                        rd.forward(request, response);
                    }
                    
                }
                
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
