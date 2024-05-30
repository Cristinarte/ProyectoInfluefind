package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Categoria;
import modelo.Influencer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

import dao.DaoCategoria;
import dao.DaoInfluencer;

/**
 * Servlet implementation class Listar
 */
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Listar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //Método que va a permitir listar lo guardado en bbdd 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	
			
			// Objeto que permite imprimir la respuesta al cliente
			PrintWriter respuesta = response.getWriter(); 
			
			//Variables necesaria para poder realizar la operacion deseada dentro del condicional
			// el parametro op indica la operacion a realizar
			int opcion = Integer.parseInt (request.getParameter("op"));
				//System.out.println("Opción recibida: " + opcion); 
			
			//Condicional que va a manejar las diferentes operaciones
			
		
			//Editar
			if (opcion == 2) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				Influencer in = new Influencer();

				try {
					//Se llama al metodo modificar
					in.modificar(id);
					// Se imprime la respuesta en formato JSON
					respuesta.print(in.dameJson());
					System.out.println(in.dameJson());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			//Listar
			}else if (opcion == 1){
				
				//Proceso para convertir en Json 
				String respuestaJSON;
				try {
					// Se obtiene la lista de influencers en formato JSON
					respuestaJSON = DaoInfluencer.getInstance().dameJson();
					System.out.println(respuestaJSON);
					// Se imprime la respuesta en formato JSON
					respuesta.print(respuestaJSON);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("El usuario tiene rol privado.");
				//response.sendRedirect("modificarInfluencer.html");
			
			//Borrar
			}else if (opcion ==3) {
				
				try {
					// Se obtiene el ID del influencer a borrar
					int id = Integer.parseInt(request.getParameter("id"));
					// Se instancia un objeto DaoInfluencer para poder borrar
					DaoInfluencer in = new DaoInfluencer();
					// Se llama al método para borrar el influencer
					in.borrar(id);
					// Se imprime la respuesta en formato JSON
					respuesta.print (in.dameJson());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	System.out.println("El usuario tiene rol privado.");
			//	response.sendRedirect("eliminarInfluencer.html");
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
