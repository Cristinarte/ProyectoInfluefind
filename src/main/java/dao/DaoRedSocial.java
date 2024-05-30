package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Categoria;
import modelo.RedSocial;

/**
 * Clase necesaria para obtener el ID de red social de la tabla redSocial de la base de datos,
 * permitiendo asignar el ID correspondiente al objeto influencer.
 * La tabla influencer tiene una clave foranea que referencia a la tabla redSocial.
 * Utiliza una conexion a la base de datos establecida por la clase DBConection.
 * Permite al sistema asignar correctamente la red social a los influencers registrados.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de Administrador</a>
 */
public class DaoRedSocial {

	//Declaramos variable para usar en la conexion
		private Connection con = null;
		
		//Creamos constructor para llamar al metodo de conexion de clase DBConection
		
		/**
		 * Constructor que inicializa una conexion a la base de datos utilizando la clase DBConection.
		 * @throws SQLException Si ocurre un error al intentar establecer la conexion a la base de datos.
		 */
		public DaoRedSocial() throws SQLException {
			con = DBConection.getConnection();
		}
		
		// MÉTODO PARA OBTENER EL ID DE LA RED SOCIAL Y PASARSELO METODO 
		/**
		 * Metodo utilizado para recuperar el id de la clase RedSocial.
		 * Este metodo busca en la tabla redSocial de la base de datos el registro correspondiente al ID proporcionado,
		 * para asi poder pasarselo al objeto influencer.
		 * @param id ID de la red social que se desea recuperar.
		 * @return Instancia de la clase RedSocial con los datos recuperados de la base de datos.
		 * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
		 */
		public RedSocial obtenerIdRedSocial (int id)throws SQLException{
			
			String sql = "SELECT * FROM redSocial WHERE IDRedSocial = ?";
			PreparedStatement ps = con.prepareStatement (sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			RedSocial r = new RedSocial (rs.getInt(1), rs.getString(2));
			return r;
		}
	
	
		
	
}
