package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Categoria;
import modelo.Comentario;
import modelo.Influencer;
import modelo.RedSocial;

/**
 * Clase necesaria para insertar y listar comentarios en la plataforma.
 * Permite insertar comentarios en la tabla de comentarios de la base de datos y recuperarlos para mostrarlos en la plataforma.
 * Utiliza una conexión a la base de datos establecida por la clase DBConection.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/criticas.html">Criticas</a>
 */

public class DaoComentario {
	
	//Declaramos variable para usar en la conexion
		private Connection con = null;
		

		//Creamos constructor para llamar al metodo de conexion de clase DBConection
		
		/**
		 * Constructor que inicializa una conexion a la base de datos utilizando la clase DBConection.
		 * @throws SQLException Si ocurre un error al intentar establecer la conexion a la base de datos.
		 */
		public DaoComentario() throws SQLException {
		    con = DBConection.getConnection();    
		}
	
		//MÉTODO DE INSERTAR
		
		
		/**
		 * Metodo utilizado para insertar un nuevo comentario en la base de datos.
		 * Inserta el texto del comentario en la tabla de comentario.
		 * @param com Objeto de tipo Comentario que contiene el texto del comentario a insertar.
		 * @throws SQLException Si ocurre algún error al interactuar con la base de datos durante la inserción del comentario.
		 */

		public void insertar (Comentario com) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement ("INSERT INTO Comentario (texto) VALUES (?)"); 
		
		ps.setString (1, com.getComentario());
			
		
		int filas = ps.executeUpdate();
		ps.close();
		
		}
		
		//MÉTODO LISTAR
		/**
		 * Metodo utilizado para listar todos los comentarios almacenados en la base de datos.
		 * Recupera todos los comentarios de la tabla de comentario y los devuelve como una lista de objetos Comentario.
		 * @return Lista de objetos Comentario que representan todos los comentarios almacenados en la base de datos.
		 * @throws SQLException Si ocurre algún error al interactuar con la base de datos durante la recuperacion de los comentarios.
		 */

		public ArrayList<Comentario> listar ()throws SQLException{
			
			PreparedStatement ps = con.prepareStatement ("SELECT * FROM Comentario");
			ResultSet rs = ps.executeQuery();
			
			ArrayList<Comentario> result = null;
			
			while (rs.next()) {
				if (result == null) {
					result = new ArrayList<>();
				}
			
				result.add(new Comentario(rs.getInt(1), rs.getString(2)));
			}
			
			
			return result;
		}
		
	
		
		//Método para convertir los datos en json:
		
		/**
		 * Metodo que genera un objeto JSON para poder listar los objetos comentario 
		 * y que puedan ser leidos correctamente en la plataforma.
		 * @return Cadena JSON que representa la lista de comentarios almacenados en la base de datos.
		 * @throws SQLException Si ocurre algun error al interactuar con la base de datos durante la generacion del JSON.
		 */
		public String dameJson() throws SQLException {
			String json = "";
			Gson gson = new Gson();
			
			json = gson.toJson(this.listar());
			
			return json;
		}
		
		
		
		
		
}
