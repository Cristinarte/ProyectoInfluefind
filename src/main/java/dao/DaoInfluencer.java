package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Categoria;
import modelo.Influencer;
import modelo.RedSocial;

/**
 * Clase que proporciona los metodos necesarios para interactuar con la tabla de influencers en la base de datos.
 * Permite a los administradores realizar operaciones CRUD en los datos de los influencers.
 * Utiliza una conexión a la base de datos establecida por la clase DBConection.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de Administrador</a>
 */


public class DaoInfluencer {
	
	//Declaramos variable para usar en la conexion
	private Connection con = null;
	
	//Variable para patron Singleton:
	private static DaoInfluencer instance = null;
	
	//Creamos constructor para llamar al metodo de conexion de clase DBConection
	
	/**
	 * Constructor que inicializa una conexion a la base de datos utilizando la clase DBConection.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexion a la base de datos.
	 */
	public DaoInfluencer() throws SQLException {
	    con = DBConection.getConnection();    
	}
	
	//PATRON SINGLETON:
	
	public static DaoInfluencer getInstance() throws SQLException {
		if (instance==null) {
			instance = new DaoInfluencer();
		}
		return instance;
	}

	
	
	
	
	//MÉTODO INSETAR CON TODO REDSOCIAL: 
	/**
	 * Metodo para insertar un nuevo objeto influencer en la base de datos.
	 * @param in Objeto Influencer que se va a insertar en la base de datos.
	 * @throws SQLException si ocurre algún error al ejecutar la consulta SQL.
	 */

	public void insertar (Influencer in) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement ("INSERT INTO influencer (nombre, nacionalidad, fechaNacimiento,ocupacion,descripcion,foto, idCategoria, IDRedSocial, urlRedSocial) VALUES (?,?,?,?,?,?,?,?,?)"); 
		
		ps.setString (1, in.getNombre());
		ps.setString (2, in.getNacionalidad());
		ps.setString (3, in.getFechaNacimiento());
		ps.setString (4, in.getOcupacion());
		ps.setString (5, in.getDescripcion());
		ps.setString (6, in.getImagen());		
		ps.setInt 	 (7, in.getCategoria().getId()); 
		ps.setInt    (8, in.getRedSocial().getId());
		ps.setString (9, in.getUrl());
		
		
		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	
	//METODO LISTAR CON CATEGORIA Y REDSOCIAL
	
	/**
	 * Metodo que devuelve una lista de todos los objetos influencers almacenados en la base de datos.
	 * @return ArrayList de Influencer que contiene todos los influencers almacenados en la base de datos.
	 * @throws SQLException si ocurre algún error al ejecutar la consulta SQL.
	 */

	public ArrayList<Influencer> listar ()throws SQLException{
		
		PreparedStatement ps = con.prepareStatement ("SELECT * FROM influencer");
		ResultSet rs = ps.executeQuery();
		
		ArrayList<Influencer> result = null;
		
		while (rs.next()) {
			if (result == null) {
				result = new ArrayList<>();
			}
			DaoCategoria dao = new DaoCategoria();
			Categoria c = dao.obtenerIdCategoria(rs.getInt(8));
			DaoRedSocial daoRed = new DaoRedSocial();
			RedSocial r = daoRed.obtenerIdRedSocial(rs.getInt(9));
			result.add(new Influencer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), c, r, rs.getString(10)));
		}
		
		
		return result;
	}
	

	
	//METODO PARA CONVERTIR DATOS EN JSON:
	
	/**
	 * Metodo que genera un objeto JSON para poder listar los objetos Influencers 
	 * y que puedan ser leidos correctamente en la plataforma.
	 * @return Cadena JSON que representa la lista de influencers.
	 * @throws SQLException si ocurre algún error al recuperar la lista de objetos influencers de la base de datos.
	 */

	
	public String dameJson() throws SQLException {
		String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(this.listar());
		
		return json;
	}
	
	//Método para poder modificar: (SERIA PARA OBTENER EL ID) CREAMOS ENTONCES EL ACTUACLIAR
	
	/**
	 * Metodo para recuperar un objeto influencer de la base de datos utilizando su ID como referencia,
	 * y asi poder realizar las operaciones que se necesiten como modificar sus atributos.
	 * @param id El ID del influencer que se desea recuperar.
	 * @return Objeto Influencer que corresponde al ID especificado.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
	 */
	 public Influencer modificar (int id) throws SQLException {
		String sql = "SELECT * FROM influencer WHERE idInfluencer=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		DaoCategoria dao = new DaoCategoria();
		Categoria c = dao.obtenerIdCategoria(rs.getInt(8));
		DaoRedSocial daoRed = new DaoRedSocial();
		RedSocial r = daoRed.obtenerIdRedSocial(rs.getInt(9));
		Influencer in = new Influencer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), c, r, rs.getString(10));
		return in;
	}
	 
	
	 
	 //METODO ACTUALIZAR CON CATEGORIA Y REDSOCIAL: 
	 
	 /**
	  * Metodo que permite actualizar los datos del objeto influencer en la base de datos.
	  * con los valores proporcionados en el objeto Influencer pasado como parámetro.
	  * @param in Objeto Influencer con los nuevos valores que se utilizarán para actualizar los datos.
	  * @throws SQLException Si ocurre un error al ejecutar la consulta SQL para actualizar los datos.
	  */
	 
	 public void actualizar (Influencer in) throws SQLException {
		 String sql = "UPDATE Influencer SET nombre = ?, nacionalidad=?, fechaNacimiento=?, ocupacion=?, descripcion=?, foto=?, idCategoria =?, IDRedSocial =?, urlRedSocial =? WHERE idInfluencer=?";
		 PreparedStatement ps = con.prepareStatement (sql); 
		 ps.setString (1, in.getNombre());
		 ps.setString (2, in.getNacionalidad());
		 ps.setString (3, in.getFechaNacimiento());
		 ps.setString (4, in.getOcupacion());
		 ps.setString (5, in.getDescripcion());
		 ps.setString (6, in.getImagen());	
		 ps.setInt(7, in.getCategoria().getId());
		 ps.setInt(8, in.getRedSocial().getId());
		 ps.setString (9, in.getUrl());
		 ps.setInt (10, in.getId());
		 
			
		 int filas = ps.executeUpdate();
		 ps.close();	 
				 
	 }
	 
	 
	//METODO BORRAR
	
	 /**
	  * Metodo que permite eliminar un objeto influencer de la base de datos utilizando el ID proporcionado.
	  * @param id ID del objeto influencer que se desea eliminar.
	  * @throws SQLException Si ocurre un error al ejecutar la consulta SQL para eliminar el objeto influencer.
	  */
	public void borrar (int id) throws SQLException {
		String sql = "DELETE FROM influencer WHERE idInfluencer=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt (1,id);
		int filas = ps.executeUpdate();
		ps.close();
	}

			
			
	//METODO BUSCAR POR CATEGORIA
	
	/**
	 * Método que permite buscar influencers en la base de datos utilizando el ID de la categoria.
	 * @param id ID de la categoria por la cual se desea buscar al influencer.
	 * @return Cadena JSON que contiene una lista de objetos influencer que pertenecen a la categoria especificada.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL para buscar los objetos influencer.
	 */
	
    
    public String buscarPorCategoria (int id) throws SQLException{
    	String sql = "SELECT * FROM influencer WHERE idCategoria=?";
    	PreparedStatement ps = con.prepareStatement(sql);
    	ps.setInt (1, id);
    	ResultSet rs = ps.executeQuery();
    	
    	ArrayList<Influencer> ls = null;
    	while (rs.next()) {
    		if (ls ==null) {
    			ls = new ArrayList<Influencer>();
    		}
    		DaoCategoria dao = new DaoCategoria();
    		Categoria c = dao.obtenerIdCategoria(rs.getInt(8));
    		DaoRedSocial daoRed = new DaoRedSocial();
    		RedSocial r = daoRed.obtenerIdRedSocial(rs.getInt(9));
    		ls.add(new Influencer (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), c, r, rs.getString(10)));
    	
    	}
    	String json = "";
		Gson gson = new Gson();
		
		json = gson.toJson(ls);
		
		return json;
    }
	

    
   
    
    
    
    
    
    
    
    
    
    

}
