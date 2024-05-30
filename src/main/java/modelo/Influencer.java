package modelo;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DBConection;
import dao.DaoCategoria;
import dao.DaoInfluencer;


/**
 * El objetivo de esta clase es representar a los influencers disponibles en la plataforma. 
 * Esta clase proporciona la estructura de datos necesaria para almacenar la informacion relevante de cada influencer, 
 * por tanto, actua como un modelo de datos para representar la informacion de los influencers en la aplicacion. 
 * Esta clase se utiliza en conjunto con el DAO para realizar operaciones CRUD, a traves de las cuales trabajare el/los administrador/es. 
 * Gracias a ello, los usuarios podran explorar y visualizar la informacion de los influencers por medio de la interfaz de la aplicacion.
 * 
 * @author: Cristina Ruiz Hernández
 * @version: 03/04/2024/ v1.0
 * @see <a href = "http://localhost:8080/Influefind/añadirInfluencer.html">Panel de administracion</a>
 * @see <a href = "http://localhost:8080/Influefind/index.html">Pagina Principal</a>
 */



public class Influencer {

	private int id;
	private String nombre;
	private String nacionalidad;
	private String fechaNacimiento;
	private String ocupacion;
	private String descripcion;
	private String imagen;
	private Categoria categoria;
	private RedSocial redSocial;
	private String url;
	
	
	public Influencer() {
		super();
	}

	
	/**
	 * Constructor para la creacion del objeto desde el formulario que inicializa todos los atributos excepto el ID.
	 * Se utiliza para crear una nueva instancia de Influencer con todos los datos necesarios,menos el atributo ID,
	 * ya que es generado automáticamente por la base de datos al insertar el influencer.
	 * Además es necesario omitir dicho atributo para usar posteriormente metodos como actualizar,
	 * evitando asi errores y asegurando un manejo adecuado del ID durante las operaciones de actualización en la base de datos.
	 * 
	 * @param nombre Define el nombre del influencer.
	 * @param nacionalidad Define la nacionalidad del influencer.
	 * @param fechaNacimiento Define la fecha de nacimiento del influencer.
	 * @param ocupacion Define la ocupación del influencer.
	 * @param descripcion Define la descripción del influencer.
	 * @param imagen Define la URL de la imagen del influencer.
	 * @param categoria Define la categoría a la que pertenece el influencer.
	 * @param redSocial Define la red social del influencer.
	 * @param url Define la URL del influencer.
	 */
	
	

	public Influencer(String nombre, String nacionalidad, String fechaNacimiento, String ocupacion, String descripcion,
			String imagen, Categoria categoria, RedSocial redSocial, String url) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.ocupacion = ocupacion;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.categoria = categoria;
		this.redSocial = redSocial;
		this.url = url;
	}

	/**
	 * Constructor que inicializa todos los atributos de un influencer, incluyendo el ID.
	 * Se utiliza para crear una nueva instancia de Influencer con todos los datos necesarios,
	 * Se incluye el atributo id para recuperar información de un influencer de la base de datos
	 * 
	 * @param id Define el id del influencer.
	 * @param nombre Define el nombre del influencer.
	 * @param nacionalidad Define la nacionalidad del influencer.
	 * @param fechaNacimiento Define la fecha de nacimiento del influencer.
	 * @param ocupacion Define la ocupación del influencer.
	 * @param descripcion Define la descripción del influencer.
	 * @param imagen Define la URL de la imagen del influencer.
	 * @param categoria Define la categoría a la que pertenece el influencer.
	 * @param redSocial Define la red social del influencer.
	 * @param url Define la URL del influencer.
	 */

	public Influencer(int id, String nombre, String nacionalidad, String fechaNacimiento, String ocupacion,
			String descripcion, String imagen, Categoria categoria, RedSocial redSocial, String url) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.ocupacion = ocupacion;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.categoria = categoria;
		this.redSocial = redSocial;
		this.url = url;
	}

	/**
	 * Metodo de inclusion del id en el objeto.
	 * @return retorna el id de tipo entero.
	 */

	public int getId() {
		return id;
	}
	
	/**
	 * Método para asignar un nuevo valor al ID del influencer.
	 * @param  asigna el id de tipo entero.
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
	 * Metodo de inclusion de la nacionalidad en el objeto.
	 * @return retorna la nacionalidad de tipo String.
	 */
	public String getNacionalidad() {
		return nacionalidad;
	}
	
	/**
	 * Método para asignar la nacionalidad en el objeto.
	 * @param  asigna la nacionalidad de tipo String.
	 */

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	
	
	/**
	 * Metodo de inclusion de la fecha de nacimiento en el objeto.
	 * @return retorna la fecha de nacimiento de tipo String.
	 */
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/**
	 * Método para asignar la fecha de nacimiento en el objeto.
	 * @param  asigna la fecha de nacimiento de tipo String.
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	/**
	 * Metodo de inclusion de la ocupacion en el objeto.
	 * @return retorna la ocupacion de tipo String.
	 */
	public String getOcupacion() {
		return ocupacion;
	}

	/**
	 * Método para asignar la ocupacion en el objeto.
	 * @param  asigna la ocupacion de tipo String.
	 */
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	/**
	 * Metodo de inclusion de la descripcion en el objeto.
	 * @return retorna la descripcion de tipo String.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Método para asignar la descripcion en el objeto.
	 * @param  asigna la descripcion de tipo String.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo de inclusion de la foto en el objeto.
	 * @return retorna la foto de tipo String.
	 */
	public String getImagen() {
		return imagen;
	}
	
	/**
	 * Método para asignar la foto en el objeto.
	 * @param  asigna la foto de tipo String.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Metodo de inclusion de la categoria en el objeto.
	 * @return retorna la categoria de tipo Categoria.
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Método para asignar la categoria en el objeto.
	 * @param  asigna la categoria de tipo Categoria.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Metodo de inclusion de la red social en el objeto.
	 * @return retorna la red social de tipo RedSocial.
	 */
	public RedSocial getRedSocial() {
		return redSocial;
	}

	/**
	 * Método para asignar la red social en el objeto.
	 * @param  asigna la red social de tipo RedSocial.
	 */
	public void setRedSocial(RedSocial redSocial) {
		this.redSocial = redSocial;
	}

	/**
	 * Metodo de inclusion de la url en el objeto.
	 * @return retorna la url de tipo String.
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Método para asignar la url en el objeto.
	 * @param asigna la url de tipo String.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	/**
	 * Metodo que retorna una representación de cadena del objeto,
	 * incluyendo  todos los atributos del influencer,
	 * @return cadena que representa al objeto.
	 */

	//TOSTRING CON REDSOCIAL
	@Override
	public String toString() {
		return "Influencer [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", fechaNacimiento="
				+ fechaNacimiento + ", ocupacion=" + ocupacion + ", descripcion=" + descripcion + ", imagen=" + imagen
				+ ", categoria=" + categoria + ", redSocial=" + redSocial + "]";
	}
	
	
	/**
	 * Metodo de inserccion en base de datos que llama al metodo insertar del DaoInfluencer,
	 * pasando el objeto  como argumento.
	 * Utiliza patrón Singleton para obtener una instancia del DaoInfluencer.
	 * @throws SQLException para gestionar errores, si hubiera, al insertar el objeto en la base de datos.
	 */
	
	public void insertar() throws SQLException {
		/*SIN PATRON SINGLETON
		 * DaoInfluencer dao = new DaoInfluencer();
		dao.insertar(this);*/
		//CON PATRON SINGLETON:
		DaoInfluencer.getInstance().insertar(this);
	}
	
	/**
	 * Metodo para obtener el id del objeto y poder trabajar con el.
	 * Llama al metodo modificar del DaoInfluencer que es el encargado de obtener ese id de la base de datos.
	 * @param id El ID del objeto que se busca.
	 * @throws SQLException para gestionar errores, si hubiera, al buscar el id del objeto en la base de datos.
	 */

	//ESTE MÉTODO ES OBTENER PORID QUE LE HE LLAMADO MODIFICAR
	public void modificar(int id) throws SQLException {
		
		DaoInfluencer dao = new DaoInfluencer();
		Influencer in = dao.modificar(id);
		
		this.setId(in.getId());
		this.setNombre(in.getNombre());
		this.setNacionalidad(in.getNacionalidad());
		this.setFechaNacimiento(in.getFechaNacimiento());
		this.setOcupacion(in.getOcupacion());
		this.setDescripcion(in.getDescripcion());
		this.setImagen(in.getImagen());
		this.setCategoria(in.getCategoria());
		this.setRedSocial(in.getRedSocial());
		this.setUrl(in.getUrl());
	}
	
	
	/**
	 * Metodo utiliza la librería Gson para convertir el objeto en JSON
	 * incluyendo en el parametro todos sus atributos.
	 * @return Cadena JSON que representa al objeto.
	 */

	public String dameJson() {
		String json = "";
		Gson gson = new Gson();
		json=gson.toJson(this);
		return json;
	}
	
	/**
	 * Metodo para actualizar objetos de la base de datos que llama al metodo actualizar del DaoInfluencer,
	 * pasando el objeto como argumento.
	 * @throws SQLException para gestionar errores, si hubiera, al actualizar el objeto en la base de datos.
	 */
	
	public void actualizar () throws SQLException {
		DaoInfluencer dao = new DaoInfluencer();
		dao.actualizar(this);
	}
	
	/**
	 * Metodo para borrar objetos de la base de datos que llama al metodo borrar del DaoInfluencer,
	 * pasando el id del objeto como argumento.
	 * @throws SQLException para gestionar errores, si hubiera, al borrar el objeto en la base de datos.
	 */
	public void borrar (int id) throws SQLException  {
		DaoInfluencer dao = new DaoInfluencer();
		dao.borrar(id);
				
	}
	
	
	
}
