package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase proporciona el metodo de conexion a base de datos, 
 * el cual sera llamado en todos los dao para poder establecer conexion a la base de datos 
 * en todas las partes del programa que interactuen con ella.
 * La conexion se establece utilizando la URL de conexión JDBC y las credenciales de usuario especificadas.
 * 
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de administracion</a>
 * @see <a href = "http://localhost:8080/Influefind/index.html">Pagina Principal</a>
 */

public class DBConection {

	
	//Variable de tipo Connection que almacena una instancia para poder conectarnos y la instanciamos a null
	public static Connection instance = null;
	//Variable que almacena la URL de conexión JDBC para la base de datos.
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/proyectoIntegrador";
	
	
	private DBConection() {
		
	}
	
	
	/**
     * Metodo estatico que permite la conexion a la base de datos.
     * Retorna una instancia de conexion a la base de datos.
     * @return conexion a la base de datos.
     * @throws SQLException para manejar errores, si hubiera, en la conexion a la base de datos.
     */
	
	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			instance = DriverManager.getConnection(JDBC_URL,props);
	
		}
		return instance;
	}
	
	
	
	
	
	
}
