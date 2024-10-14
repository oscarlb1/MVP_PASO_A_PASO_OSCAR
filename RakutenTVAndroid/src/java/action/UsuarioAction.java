package action;

import DAO.PeliculaDAO;
import DAO.UsuarioDAO;
import interfaces.Action;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pelicula;
import model.Usuario;


public class UsuarioAction implements Action{

    @Override
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "LOGIN":
                cadDestino = login(request, response);
                break;
        }
        return cadDestino;
    }
    
    private String login(HttpServletRequest request, 
                         HttpServletResponse response) {
        //PARÁMETROS
        String email =  request.getParameter("EMAIL");
        String password = request.getParameter("PASSWORD");
        Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setPassword(password);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ArrayList<Usuario> usuarios 
                = usuarioDao.findAll(usuario);
        return Usuario.toArrayJSon(usuarios);
        //return Pelicula.toCadena(peliculas.get(0));
    }
}
