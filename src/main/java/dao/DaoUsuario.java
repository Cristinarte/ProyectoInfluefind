package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Categoria;
import modelo.Influencer;
import modelo.RedSocial;
import modelo.Usuario;



/**
 * Clase necesaria para insertar nuevos usuarios en la plataforma asignándoles un rol específico, con el proposito de redirigirlos a la pagina correspondiente.
 * Dichos roles determinan las paginas web a las que pueden acceder.
 * Permite insertar usuarios en la tabla de usuario de la base de datos.
 * Utiliza una conexión a la base de datos establecida por la clase DBConection.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/registro.html">Registro</a>
 */

public class DaoUsuario {

	//Declaramos variable para usar en la conexion
	private Connection con = null;
	
	
	//Creamos constructor para llamar al metodo de conexion de clase DBConection
	
	/**
	 * Constructor que inicializa una conexion a la base de datos utilizando la clase DBConection.
	 * @throws SQLException Si ocurre un error al intentar establecer la conexion a la base de datos.
	 */

	public DaoUsuario() throws SQLException {
		con = DBConection.getConnection();
	}
		
	/**
	 * Metodo utilizado para insertar un nuevo usuario en la tabla de usuarios de la base de datos.
	 * @param us Objeto Usuario que se va a insertar en la base de datos.
	 * @throws SQLException Si ocurre algún error al interactuar con la base de datos durante la inserción.
	 */

	public void insertar (Usuario us) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement ("INSERT INTO usuario (nombre, apellidos, email,password,rol) VALUES (?,?,?,?,?)"); 
		
		ps.setString (1, us.getNombre());
		ps.setString (2, us.getApellidos());
		ps.setString (3, us.getEmail());
		ps.setString (4, us.getPassword());
		ps.setString (5, us.getRol());

		int filas = ps.executeUpdate();
		ps.close();
		
	}
	
	/**
	 * Metodo que permite autenticar un usuario en la base de datos utilizando su correo electronico y contrasenia.
	 * @param u Objeto Usuario que contiene el correo electronico del usuario que desea autenticarse.
	 * @param pass La contrasenia del usuario que desea autenticarse.
	 * @return Un objeto Usuario que contiene los datos del usuario autenticado.
	 * @throws SQLException Si ocurre un error al ejecutar la consulta SQL para autenticar el usuario.
	 */
	
	public Usuario logeando (Usuario u, String pass) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE email=? AND password=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString (1, u.getEmail());
		ps.setString (2, pass);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		Usuario aux = new Usuario (rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
		
		return aux;
	}
	
	
	 
	
}
