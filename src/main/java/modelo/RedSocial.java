package modelo;

import java.sql.SQLException;

import dao.DaoCategoria;
import dao.DaoInfluencer;
import dao.DaoRedSocial;


/**
 * Clase que representa una red social para cada objeto influencer de la plataforma.
 * Cada instancia de esta clase contiene un identificador único y un nombre que describe la red social.
 * La red social se utiliza para que cada objeto de tipo influencer tenga la que le corresponda, que en este caso es solo una (Instragram).
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de administracion</a>
 */

public class RedSocial {

	private int id;
	private String nombrePlataforma;
	
	
	
	/**
	 * Constructor que inicializa todos los atributos de la red social.
	 * Se utiliza para crear una nueva instancia de Redsocial con todos los datos necesarios.
	 * @param id Define el id de la red social.
	 * @param nombreCategoria Define el nombre de la red social.
	 */
	
	public RedSocial(int id, String nombrePlataforma) {
		super();
		this.id = id;
		this.nombrePlataforma = nombrePlataforma;
	}


	
	
	
	/*public RedSocial(int id) {
		super();
		this.id = id;
	}*/

	

	/**
	 * Constructor para la creacion del objeto desde el formulario que inicializa unicamente el nombre de la red social.
	 * Se utiliza para crear una nueva instancia de RedSocial con todos los datos necesarios,menos el atributo ID,
	 * ya que es generado automáticamente por la base de datos al insertar la red social.
	 * Además es necesario omitir dicho atributo para usar posteriormente metodos como actualizar,
	 * evitando asi errores y asegurando un manejo adecuado del ID durante las operaciones de actualización en la base de datos.
	 * 
	 * @param nombrePlataforma Define el nombre de la red social.
	 */
	
	public RedSocial(String nombrePlataforma) {
		super();
		this.nombrePlataforma = nombrePlataforma;
	}


	
	/**
	 * Metodo de inclusion del id en el objeto.
	 * @return retorna el id de tipo entero.
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * Método para asignar un nuevo valor al ID de la red social.
	 * @param  asigna el id de tipo entero.
	 */
	
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * Metodo de inclusion del nombre en el objeto.
	 * @return retorna el nombre de tipo String.
	 */
	public String getNombrePlataforma() {
		return nombrePlataforma;
	}


	/**
	 * Método para asignar un nombre en el objeto.
	 * @param  asigna el nombre de tipo String.
	 */
	public void setNombrePlataforma(String nombrePlataforma) {
		this.nombrePlataforma = nombrePlataforma;
	}

	
	/**
	 * Metodo que retorna una representación de cadena del objeto,
	 * incluyendo  todos los atributos de la red social,
	 * @return cadena que representa al objeto.
	 */

	@Override
	public String toString() {
		return "RedSocial [id=" + id + ", nombrePlataforma=" + nombrePlataforma + "]";
	}
	

	/**
	 * Metodo utilizado para llamar al metodo de obtenerIdRedSocial del DaoRedSocial  
	 * cuyo objetivo es obtener el identificador de la red social desde la base de datos, 
	 * con el fin de asignarlo al atributo de tipo RedSocial en el modelo Influencer.
	 * @param id Identificador de la red social a obtener.
	 * @throws SQLException para gestionar errores durante la obtención del id desde la base de datos.
	 */

	public void obtenerIdRedSocial (int id) throws SQLException {
		
		DaoRedSocial dao = new DaoRedSocial();
		RedSocial aux = dao.obtenerIdRedSocial(id);
		
		this.setId(aux.getId());
		this.setNombrePlataforma(aux.getNombrePlataforma());
		
	}

	
	
	
}
