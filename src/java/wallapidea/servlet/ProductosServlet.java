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
import wallapidea.dao.CategoriaFacade;
import wallapidea.entity.Categoria;
import wallapidea.entity.Producto;
import wallapidea.entity.Usuario;
import wallapidea.service.BuscarProductoService;

/**
 *
 * @author ivanl
 */
@WebServlet(name = "ProductosServlet", urlPatterns = {"/ProductosServlet"})
public class ProductosServlet extends HttpServlet {

    @EJB
    private BuscarProductoService buscarProductoService;
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
        
        
        String buscar = request.getParameter("busqueda");
        String modo = request.getParameter("modoBusqueda");
        
        List<Producto> productos = null;
        List<Categoria> categorias = categoriaFacade.findAll();

        HttpSession session = request.getSession();
        
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        if(modo == null){
            productos = buscarProductoService.getAll(u.getUsuarioId());
        }else{
            switch (modo) {
                case "Todo":
                    productos = buscarProductoService.getAll(u.getUsuarioId());
                    break;
                case "Recientes":
                    productos = buscarProductoService.getRecentProducts(u.getUsuarioId());
                    break;
                    
                case "TituloDescripcion":
                    productos = buscarProductoService.findByDescOrTitle(buscar, u.getUsuarioId());
                    break;
                    
                case "Categoria":
                    int categoria_id = Integer.parseInt(request.getParameter("Categoria"));
                    productos = buscarProductoService.findByCatId(categoria_id, u.getUsuarioId());
                    break;
                    
                case "PalabrasClave":
                    productos = buscarProductoService.findByKeys(buscar, u.getUsuarioId());
                    break;
                    
                case "Fecha":
                    String fecha = request.getParameter("Calendario");
                    productos = buscarProductoService.getProductByDate(u.getUsuarioId(), fecha);
                    break;
                default:
                    productos = buscarProductoService.getAll(u.getUsuarioId());
                    break;
            }       
        }
        
        request.setAttribute("productos", productos);
        request.setAttribute("categorias", categorias);
        
       if(u.getIsadmin()){
           RequestDispatcher rd = request.getRequestDispatcher("ListadoProductos.jsp");
            rd.forward(request, response);
       }else{
           RequestDispatcher rd = request.getRequestDispatcher("PanelProductos.jsp");
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
