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
import javax.servlet.http.HttpSession;
import static javax.ws.rs.core.Response.status;
import wallapidea.entity.Usuario;
import wallapidea.dao.UsuarioFacade;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/InicioSesionServlet"})
public class InicioSesionServlet extends HttpServlet {
   @EJB
    private UsuarioFacade usuarios;
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
        HttpSession session = request.getSession();
        
        //OBTENEMOS LOS PARAMETROS DEL FORM DE iniciosesion.JSP
        String user= request.getParameter("user");
        String pass= request.getParameter("pass");
        String status= "SIN STATUS";
        
        try{
            System.out.println(user);
            usuario = this.usuarios.findByNombre(user);        
        }
        catch(Exception exc){
            status=exc.getMessage();
            System.out.println(status);
        }
        if (usuario == null || !usuario.getPass().equals(pass) ) {             
            status = "El usuario o la contraseña es incorrecto";
            request.setAttribute("status", status);
            rd = request.getRequestDispatcher("InicioSesion.jsp");
        } else { // el usuarioestá y la clave es correcta     
            if(usuario.getIsadmin()){ // Si es Administrador accede al panel de administrador
             session.setAttribute("usuario", usuario);
            status="Bienvenido: "+usuario.getNombre();
            request.setAttribute("status", status);
            // Lista de todos los usuarios
            List<Usuario> listaUsuarios = usuarios.findAll();
            request.setAttribute("listaUsuarios", listaUsuarios);
            rd = request.getRequestDispatcher("PerfilAdministrador.jsp");
            //
            }else{
            session.setAttribute("usuario", usuario);
            status="Bienvenido: "+usuario.getNombre();
            request.setAttribute("status", status);    
            rd = request.getRequestDispatcher("PerfilUsuario.jsp");
            }
        }      

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
