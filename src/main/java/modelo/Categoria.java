package modelo;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import dao.DaoCategoria;
import dao.DaoInfluencer;
import dao.DaoUsuario;


/**
 * Clase que representa una categoria para cada objeto influencer de la plataforma.
 * Cada instancia de esta clase contiene un identificador unico y un nombre que describe la categoria.
 * Las categorias se utilizan para que cada objeto de tipo influencer tenga la que le corresponda.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de administracion</a>
 */



public class Categoria {
	
	private int id;
	public String nombreCategoria;


	public Categoria() {
		super();
	}

	/**
	 * Constructor que inicializa todos los atributos de la categoria.
	 * Se utiliza para crear una nueva instancia de Categoria con todos los datos necesarios,
	 * Se incluye el atributo id para recuperar información de una categoria de la base de datos
	 * @param id Define el id de la categoria.
	 * @param nombreCategoria Define el nombre de la categoria.
	 */
	
	public Categoria(int id, String nombreCategoria) {
		super();
		this.id = id;
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * Constructor para la creacion del objeto desde el formulario que inicializa unicamente el nombre de la categoria.
	 * Se utiliza para crear una nueva instancia de Categoria con todos los datos necesarios,menos el atributo ID,
	 * ya que es generado automáticamente por la base de datos al insertar la categoria.
	 * Además es necesario omitir dicho atributo para usar posteriormente metodos como actualizar,
	 * evitando asi errores y asegurando un manejo adecuado del ID durante las operaciones de actualización en la base de datos.
	 * 
	 * @param nombreCategoria Define el nombre de la categoria.
	 */
	
	public Categoria(String nombreCategoria) {
		super();
		this.nombreCategoria = nombreCategoria;
		
	}
	
	
	
	/**
	 * Metodo de inclusion del id en el objeto.
	 * @return retorna el id de tipo entero.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método para asignar un nuevo valor al ID de la categoria.
	 * @param  asigna el id de tipo entero.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Metodo de inclusion del nombre en el objeto.
	 * @return retorna el nombre de tipo String.
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	
	/**
	 * Método para asignar un nombre en el objeto.
	 * @param  asigna el nombre de tipo String.
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	/**
	 * Metodo que retorna una representación de cadena del objeto,
	 * incluyendo  todos los atributos de la categoria,
	 * @return cadena que representa al objeto.
	 */

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombreCategoria=" + nombreCategoria + "]";
	}

	

	/**
	 * Metodo utilizado para llamar al metodo de obtenerIdCategoria del DaoCategoria  
	 * cuyo objetivo es obtener el identificador de una categoria desde la base de datos, 
	 * con el fin de asignarlo al atributo de tipo Categoria en el modelo Influencer.
	 * @param id Identificador de la categoría a obtener.
	 * @throws SQLException para gestionar errores durante la obtención del id desde la base de datos.
	 */

	public void obtenerIdCategoria (int id) throws SQLException {
		
		DaoCategoria dao = new DaoCategoria();
		Categoria aux = dao.obtenerIdCategoria(id);
		
		this.setId(aux.getId());
		this.setNombreCategoria(aux.getNombreCategoria());
		
	}
	
	

	
}
