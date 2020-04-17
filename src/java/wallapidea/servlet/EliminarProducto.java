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
import wallapidea.dao.ProductoFacade;
import wallapidea.dao.ValoracionFacade;
import wallapidea.entity.Producto;
import wallapidea.entity.Usuario;
import wallapidea.service.BuscarProductoService;

/**
 *
 * @author David
 */
@WebServlet(name = "EliminarProducto", urlPatterns = {"/EliminarProducto"})
public class EliminarProducto extends HttpServlet {

    @EJB
    private BuscarProductoService buscarProductoService;

    @EJB
    private ProductoFacade productoFacade;
    @EJB
    private ValoracionFacade valFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("usuario");
        //pasamos por parámetro el id del producto a eliminar
        String idS = request.getParameter("idProducto");
        System.out.println("ID DEL PRODUCTO A ELIMINAR: __" + idS);
        // obtenemos el producto
        Integer id;
        id = Integer.parseInt(idS);
        Producto producto = productoFacade.find(id);
        //eliminamos sus valoraciones
        valFacade.deleteByProduct(producto);

        // eliminamos de la lista de productos de usuario 
        List<Producto> lista = u.getProductoList();
        lista.remove(producto);
        u.setProductoList(lista);
        // lo elimninamos
        System.out.println("PRODUCTO A ELIMINAR: " + producto.getTitulo());
        // ver como hacer el cascade para que elimine demás relaciones en base de datos 
        productoFacade.remove(producto);

        List<Producto> productos = buscarProductoService.getAll(u.getUsuarioId());

        /// hay que controlar como se llama realmente esta jsp 
        // Si es admin lo envia al panel de admin de productos
        if (u.getIsadmin()) {
            request.setAttribute("productos", productos);
            rd = request.getRequestDispatcher("ListadoProductos.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("PerfilUsuario.jsp");
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
