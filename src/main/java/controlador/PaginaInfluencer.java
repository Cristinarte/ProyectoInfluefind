package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.google.gson.Gson;
import dao.DaoInfluencer;
import modelo.Categoria;
import modelo.Influencer;


/**
 * Servlet implementation class PaginaInfluencer
 */
public class PaginaInfluencer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaInfluencer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		PrintWriter respuesta = response.getWriter(); 
        String respuestaJSON;

        try {
            // Se obtiene el ID del influencer desde el parámetro de la solicitud
            int idInfluencer = Integer.parseInt(request.getParameter("id"));

            // Obtener el influencer con el ID dado
            Influencer influencer = DaoInfluencer.getInstance().modificar(idInfluencer);

            // Convertir el influencer a JSON
            Gson gson = new Gson();
            respuestaJSON = gson.toJson(influencer);

            // Imprimir la respuesta JSON en la salida de la respuesta
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            respuesta.print(respuestaJSON);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error accessing database");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid influencer ID");
        }
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
