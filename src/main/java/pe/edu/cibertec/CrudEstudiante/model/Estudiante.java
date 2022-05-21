package pe.edu.cibertec.CrudEstudiante.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Estudiante")
public class Estudiante {

	//id auto incrementada
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column (name = "id_estudiante")
	private long id;
	
	//@NotEmpty(message = "campo nombre no puede estar vacio") @Size( min = 4, max = 255, message = "campo nombre error")
	@Column (name = "nom")
	private String nombre;
	
	//@NotEmpty(message = "campo apellido no puede estar vacio") @Size( min = 4, max = 255, message = "campo apellido error")
	@Column (name = "apell")
	private String apellido;
	
	//@Min(value = 0)
	@Column (name = "eda")
	private int edad;
	
	//@NotEmpty(message = "campo direccion no puede estar vacio") @Size( min = 4, max = 300, message = "campo nombre error")
	@Column (name = "direc")
	private String direccion;
	
	//@NotEmpty(message = "campo img no puede estar vacio")
	@Column (name = "img")
	private String urlimg;
	
	@Column (name = "id_cur")
	private long curso;


	public long getCurso() {
		return curso;
	}


	public void setCurso(long curso) {
		this.curso = curso;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getUrlimg() {
		return urlimg;
	}


	public void setUrlimg(String urlimg) {
		this.urlimg = urlimg;
	}


	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", direccion=" + direccion + ", urlimg=" + urlimg + ", curso=" + curso + "]";
	}
	
}
