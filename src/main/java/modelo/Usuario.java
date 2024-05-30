package modelo;

import java.sql.SQLException;

import dao.DaoInfluencer;
import dao.DaoUsuario;

/**
 * Clase que representa a los usuarios de la plataforma, quienes tendran diferentes roles para acceder a distintas funcionalidades del sistema. 
 * Esta clase permite registrar tanto usuarios publicos como privados y asignarles roles específicos para su uso en la plataforma. 
 * Dichos roles determinaran el acceso a las distintas paginas de la web correspondientes.
 * @author Cristina Ruiz Hernández
 * @version 03/04/2024/ v1.0
 * @see <a href="http://localhost:8080/Influefind/registro.html">Registro</a>
 */

public class Usuario {
	
	int id;
	String nombre;
	String apellidos;
	String email;
	String password;
	String rol;
	
	
	
	public Usuario() {
		super();
	}

	/**
	 * Constructor que inicializa todos los atributos del usuario.
	 * Se utiliza para crear una nueva instancia de Usuario con todos los datos necesarios.
	 * @param id Define el id del usuario.
	 * @param nombre Define el nombre del usuario.
	 * @param apellidos Define los apellidos del usuario.
	 * @param email Define el email del usuario.
	 * @param password Define la contrasenia del usuario.
	 * @param rol Define el rol del usuario.
	 */
	public Usuario(int id, String nombre, String apellidos, String email, String password, String rol) {
		
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}

	/**
	 * Constructor para la creacion del objeto desde el formulario que inicializa todos los atributos menos el id.
	 * Se utiliza para crear una nueva instancia de Usuario sin el id,
	 * ya que este es generado automáticamente por la base de datos al insertar el usuario.
	 * @param nombre Define el nombre del usuario.
	 * @param apellidos Define los apellidos del usuario.
	 * @param email Define el email del usuario.
	 * @param password Define la contrasenia del usuario.
	 * @param rol Define el rol del usuario.
	 */
	public Usuario(String nombre, String apellidos, String email, String password, String rol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}


	/**
	 * Metodo de inclusion del id en el objeto.
	 * @return retorna el id de tipo entero.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Método para asignar un nuevo valor al ID del usuario.
	 * @param asigna el id de tipo entero.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo de inclusion del nombre en el objeto.
	 * @return retorna el nombre de tipo String.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método para asignar un nombre en el objeto.
	 * @param  asigna el nombre de tipo String.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo de inclusion del apellido en el objeto.
	 * @return retorna el apellido de tipo String.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Método para asignar un apellido en el objeto.
	 * @param  asigna el apellido de tipo String.
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Metodo de inclusion del email en el objeto.
	 * @return retorna el email de tipo String.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método para asignar un email en el objeto.
	 * @param  asigna el email de tipo String.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo de inclusion de la contrasenia en el objeto.
	 * @return retorna la contrasenia de tipo String.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Método para asignar la contrasenia en el objeto.
	 * @param  asigna la contrasenia de tipo String.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * Metodo de inclusion un rol al objeto.
	 * @return retorna el rol de tipo String.
	 */
	public String getRol() {
		return rol;
	}
	
	/**
	 * Método para asignar un rol en el objeto.
	 * @param  asigna el rol de tipo String.
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	
	/**
	 * Metodo que retorna una representación de cadena del objeto,
	 * incluyendo  todos los atributos del usuario,
	 * @return cadena que representa al objeto.
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", rol=" + rol + "]";
	}
	
	/**
	 * Metodo de inserccion en base de datos que llama al metodo insertar del DaoUsuario,
	 * pasando el objeto  como argumento.
	 * @throws SQLException para gestionar errores, si hubiera, al insertar el objeto en la base de datos.
	 */
	public void insertar() throws SQLException {
		
		DaoUsuario dao = new DaoUsuario();
		dao.insertar(this);
		
	}
	
	//Metodo de logeo
	
	/**
	 * Metodo que permite iniciar la sesion de un usuario en el sistema utilizando su contrasenia.
	 * @param pass La contrasenia con la que el usuario intenta iniciar sesion.
	 * @return valor booleano que indica si el inicio de sesion fue exitoso o no.
	 * @throws SQLException Si ocurre un error al intentar iniciar sesión debido a problemas con la base de datos.
	 */
	
	public boolean logeo (String pass) throws SQLException {
		boolean ok = false;
		
		DaoUsuario dao = new DaoUsuario ();
		Usuario aux = dao.logeando (this, pass); //bdd
		
		if (aux != null) {
			ok = true;
			this.setId(aux.getId());
			this.setNombre(aux.getNombre());
			this.setApellidos(aux.getApellidos());
			this.setEmail(aux.getEmail());
			this.setPassword(aux.getPassword());
			this.setRol(aux.getRol());
		}
		
		return ok;
	}
	
	
	
	
	
	
	
}
