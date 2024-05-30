package modelo;

import java.sql.SQLException;

import dao.DaoComentario;



/**
 * Clase que representa los comentarios de la plataforma. 
 * Estos comentarios permiten a cualquier usuario expresar sus opiniones y sugerencia 
 * con el fin de mejorar continuamente la experiencia de la plataforma.
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/criticas.html">Criticas</a>
 */



public class Comentario {

	private int id;
	private String comentario;
	
	/**
	 * Constructor que inicializa todos los atributos del comentario.
	 * Se utiliza para crear una nueva instancia de Comentario con todos los datos necesarios.
	 * @param id Define el id del comentario.
	 * @param comentario Define el contenido o texto del comentario.
	 */
	
	public Comentario(int id, String comentario) {
		super();
		this.id = id;
		this.comentario = comentario;
	}

	/**
	 * Constructor para la creacion del objeto desde el formulario que inicializa unicamente el contenido del comentario.
	 * Se utiliza para crear una nueva instancia de Comentario unicamente con el texto sin el id,
	 * ya que este es generado automáticamente por la base de datos al insertar el comentario.
	 * @param nombreCategoria Define el texto del comentario.
	 */
	
	public Comentario(String comentario) {
		super();
		this.comentario = comentario;
	}
	
	
	/**
	 * Metodo de inclusion del id en el objeto.
	 * @return retorna el id de tipo entero.
	 */

	public int getId() {
		return id;
	}

	/**
	 * Método para asignar un nuevo valor al ID del comentario.
	 * @param asigna el id de tipo entero.
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo de inclusion del comentario en el objeto.
	 * @return retorna el comentario de tipo String.
	 */
	
	public String getComentario() {
		return comentario;
	}

	/**
	 * Método para asignar un comentario en el objeto.
	 * @param  asigna el comentario de tipo String.
	 */
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * Metodo que retorna una representación de cadena del objeto,
	 * incluyendo  todos los atributos del comentario,
	 * @return cadena que representa al objeto.
	 */
	
	@Override
	public String toString() {
		return "Comentario [id=" + id + ", comentario=" + comentario + "]";
	}
	
	/**
	 * Metodo de inserccion en base de datos que llama al metodo insertar del DaoComentario,
	 * pasando el objeto  como argumento.
	 * @throws SQLException para gestionar errores, si hubiera, al insertar el objeto en la base de datos.
	 */
	public void insertar() throws SQLException {
		DaoComentario dao = new DaoComentario();
		dao.insertar(this);
	}
	
	
	
}
