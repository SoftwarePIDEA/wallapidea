/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wallapidea.service;

import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import wallapidea.dao.UsuarioFacade;
import wallapidea.entity.Usuario;

/**
 *
 * @author Pablo
 */
@Stateless
public class UsuarioService {

    @EJB
    private UsuarioFacade usuarioFacade;

    public void Anyadir(String user, String pass, Boolean admin) {
        Usuario usuario = new Usuario();
        usuario.setNombre(user);
        usuario.setPass(pass);
        usuario.setIsadmin(admin);
        usuarioFacade.create(usuario);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Usuario> BuscarPorNombreoID(String search) {
        List<Usuario> lista = new LinkedList<>();
        Usuario us;
        try {
            int id = Integer.valueOf(search);
            us = usuarioFacade.findByID(id);
            if (us != null) {
                lista.add(us);
            }

        } catch (Exception exc) {
            System.out.println(exc.toString());
            System.out.println("NO HAY USUARIOS CON ESE ID");
        }
        try {
            us = usuarioFacade.findByNombre(search);
            if (us != null) {
                lista.add(us);
            }
        } catch (Exception exc) {
            System.out.println(exc.toString());
            System.out.println("NO HAY USUARIOS CON ESE NOMBRE");
        }

        return lista;
    }

}
