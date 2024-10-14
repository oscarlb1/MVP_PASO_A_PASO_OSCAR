package action;

import DAO.PeliculaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pelicula;

public class PeliculaAction implements interfaces.Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String cadDestino = "";
        String action = (String) request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch (arrayAction[1]) {
            case "FIND_ALL":
                cadDestino = findAll(request, response);
                break;
        }
        return cadDestino;
    }
    private String findAll(HttpServletRequest request, 
                                HttpServletResponse response) {
        PeliculaDAO peliculaDao = new PeliculaDAO();
        ArrayList<Pelicula> lstPeliculas = peliculaDao.findAll(null);
        return Pelicula.toArrayJSon(lstPeliculas);
    }

    
}
