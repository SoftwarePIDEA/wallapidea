/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import wallapidea.dao.CategoriaFacade;
import wallapidea.dao.PalabraclaveFacade;
import wallapidea.dao.ProductoFacade;
import wallapidea.entity.Categoria;
import wallapidea.entity.Palabraclave;
import wallapidea.entity.Producto;
import wallapidea.entity.Usuario;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "AnyadirProducto", urlPatterns = {"/AnyadirProducto"})
public class AnyadirProducto extends HttpServlet {
    @EJB
    CategoriaFacade categoriaFacade;
    @EJB
    ProductoFacade prodFacade;
    @EJB
    PalabraclaveFacade palabraclaveFacade;
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
        Usuario u = (Usuario) session.getAttribute("usuario");
        
        String categoriaId = request.getParameter("cat");
        String titulo = new String(request.getParameter("titulo").getBytes(),"UTF-8");
        String descripcion = new String(request.getParameter("descripcion").getBytes(),"UTF-8");
        String precio = request.getParameter("precio");
        String foto = request.getParameter("foto");
        String pCs = new String(request.getParameter("palabrasclave").getBytes(),"UTF-8");
        pCs = pCs.replaceAll("\\s+","").toUpperCase();
        String[] palabrasClave = pCs.split(",");
        
        Producto producto = new Producto();
        Categoria categoria = categoriaFacade.find(Integer.parseInt(categoriaId));
        
        //Creamos la lista de productos que va a tener una pclave(listaProd) y la lista de palabras clave que va a tener nuestro producto (listaClave)
        List<Palabraclave> listaClave = new LinkedList<>(); //siempre es nueva ya que las cogemos del campo de texto (textarea)
        List<Producto> listaProd; //La lista de productos de una palabra clave existe ya si la palabra ya era palabra clave.
        //añadimos nuevas palabras clave
        Palabraclave pclave;
        //Empezamos a recorrer las palabras claves escritas por el usuario y las añadimos a la lista de pclaves que va a tener nuestro producto.
        if(palabrasClave.length>0 && !palabrasClave[0].equals("")){
            for (String palabra : palabrasClave) {
                  palabra = new String(palabra.getBytes(),"UTF-8");
                if (!palabraclaveFacade.existsPalabra(palabra)) { //creamos una pclave si antes no existia en la bd
                    pclave= new Palabraclave();
                    listaProd= new LinkedList<>();
                    pclave.setPalabra(palabra);
                    pclave.setProductoList(listaProd);
                    palabraclaveFacade.create(pclave);             
                }
                pclave= palabraclaveFacade.findByPalabra(palabra); //se busca esa palabraclave (estamos seguros de que essta porque si no estaba la hemos creado antes.)
                //editamos la lista de pclaves que tiene nuestro producto
                if(!listaClave.contains(pclave)){
                    listaClave.add(pclave);
                }              
            }
        }       
        //RELLENAR PRODUCTO
        producto.setUsuarioId(u);
        producto.setCatId(categoria);
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setPrecio(Double.parseDouble(precio));
        producto.setFoto(foto);
        producto.setPalabraclaveList(listaClave);
        producto.setFechayhora(new Date());
        
        //CREAMOS EL PRODUCTO
        prodFacade.create(producto);
        
        //Añadimos nuestro producto ya creado a todas las palabras clave
        if(palabrasClave.length>0 && !palabrasClave[0].equals("")){
            for (String palabra : palabrasClave){
                palabra = new String(palabra.getBytes(),"UTF-8");
                pclave= palabraclaveFacade.findByPalabra(palabra); //se busca esa palabraclave (estamos seguros de que essta porque si no estaba la hemos creado antes.)
                listaProd = pclave.getProductoList(); //vemos su lista de productos (si es nueva o no tiene ningun producto estara vacía)
                listaProd.add(producto); //le añadimos nuestro producto (si ya está..?)
                pclave.setProductoList(listaProd);  
                palabraclaveFacade.edit(pclave); 
            }
        }
        
        //LISTAPRODUCTOS
        List<Producto> lista = u.getProductoList();
        lista.add(producto);
        u.setProductoList(lista);
        //VOLVEMOS
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
