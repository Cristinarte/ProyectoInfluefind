package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Categoria;
import modelo.Influencer;
import modelo.Usuario;



/**
 * Clase necesaria para obtener el ID de categoría de la tabla categoria de la base de datos,
 * permitiendo asignar el ID correspondiente al objeto influencer.
 * La tabla influencer tiene una clave foranea que referencia a la tabla de categoria.
 * Utiliza una conexion a la base de datos establecida por la clase DBConection.
 * Permite al sistema asignar correctamente las categorias a los influencers registrados.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de Administrador</a>
 */

public class DaoCategoria {
	//Declaramos variable para usar en la conexion
	private Connection con = null;
	//Variable para patron Singleton:
	private static DaoCategoria instance = null;
	//Creamos constructor para llamar al metodo de conexion de clase DBConection
	
	/**
	 * Constructor que inicializa una conexion a la base de datos utilizando la clase DBConection.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexion a la base de datos.
	 */
	public DaoCategoria() throws SQLException {
		con = DBConection.getConnection();
	}
	//PATRON SINGLETON:
	public static DaoCategoria getInstance() throws SQLException {
		if (instance==null) {
			instance = new DaoCategoria();
		}
		return instance;
	}

	
	//metodo para listar las Categorias de la bdd
	
	
	
	//NUEVO MÉTODO PARA OBTENER EL ID DE LA CATEGORIA Y PASARSELO METODO EN CLASES PARTICULARES
	/**
	 * Metodo utilizado para recuperar el id de la clase Categoria.
	 * Este metodo busca en la tabla categoria de la base de datos el registro correspondiente al ID proporcionado,
	 * para asi poder pasarselo al objeto influencer.
	 * @param id ID de la categoria que se desea recuperar.
	 * @return Instancia de la clase Categoria con los datos recuperados de la base de datos.
	 * @throws SQLException Si ocurre algún error al interactuar con la base de datos.
	 */

	public Categoria obtenerIdCategoria (int id)throws SQLException{
		
		String sql = "SELECT * FROM categoria WHERE idCategoria = ?";
		PreparedStatement ps = con.prepareStatement (sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Categoria ca = new Categoria (rs.getInt(1), rs.getString(2));
		
		return ca;
	}
	
	

	
	
	
	
		

	
	
}
