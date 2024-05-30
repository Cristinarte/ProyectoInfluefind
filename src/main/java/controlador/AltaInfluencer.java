package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.Categoria;
import modelo.Influencer;
import modelo.RedSocial;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import dao.DaoCategoria;
import dao.DaoInfluencer;
import dao.DaoRedSocial;

/**
 * Servlet implementation class AltaInfluencer
 */


//Anotacion para cargar archivos y enviar datos del formulario que incluyen archivos adjuntos.
@MultipartConfig

public class AltaInfluencer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Variable con la ruta de las imagenes donde van a ser guardadas:
	private String pathFiles = "/Users/cristinaruizhernandez/eclipse/Influefind/src/main/webapp/css/fotos";
	
	//Objeto que direcciona donde se van a guardar las fotos:
	private File uploads = new File (pathFiles);
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaInfluencer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Variables para guardar los datos obtenidos del formulario
		
		String nombre = request.getParameter("nombre");
		String nacionalidad = request.getParameter("nacionalidad");
		String fechaNacimiento = request.getParameter("fechaNacimiento");
		String ocupacion = request.getParameter ("ocupacion");
		String descripcion = request.getParameter("descripcion");
		String imagen = request.getParameter("imagen");
		String categoria = request.getParameter("categorias");
	
		String id = request.getParameter("id");

		String rs = request.getParameter("rrss");
		String url = request.getParameter("url");
		

		// Obtener datos de la imagen desde el cliente
		
		Part part = request.getPart("imagen");
		Path path = Paths.get(part.getSubmittedFileName());
		String fileName = path.getFileName().toString();
		
		// Preparar el InputStream para leer los datos del archivo de imagen
		InputStream input = part.getInputStream();
		
		//Proceso para copiar la foto
		File file = new File (uploads, fileName);
		
		try {
			Files.copy(input, file.toPath());
		}catch (Exception e) {
			System.out.println("Error en la copia del archivo"); 
			PrintWriter error = response.getWriter();
			error.print("Se ha producido un error, contacte con el administrador");
		}
		

		// Crear objeto Influencer con los datos del formulario y el archivo
		Influencer in;
		try {
	
			DaoCategoria ca = new DaoCategoria();
			Categoria c = ca.obtenerIdCategoria(Integer.parseInt(categoria));
			
			DaoRedSocial redSocial = new DaoRedSocial();
			RedSocial r = redSocial.obtenerIdRedSocial(Integer.parseInt(rs));
		

			in = new Influencer (nombre, nacionalidad, fechaNacimiento, ocupacion, descripcion, fileName, c, r ,url);
			if (id.isEmpty()) {
				DaoInfluencer dao = new DaoInfluencer();
				dao.insertar(in);
			}else {
				int idInt=Integer.parseInt(id);
				in.setId(idInt);
				in.actualizar();
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Redirigir a la pagina de modificacion de influencer
		response.sendRedirect("modificarInfluencer.html");
	}
}
