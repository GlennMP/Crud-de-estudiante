package pe.edu.cibertec.CrudEstudiante.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity // crea la tabla 
@Table(name = "estudiante")
public class Estudiante {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY) //id auto incrementada
	private long id;
	
	//@NotEmpty(message = "campo nombre no puede estar vacio") @Size( min = 3, max = 50, message = "campo nombre error")
	@Column (name = "nom" ,nullable = false) // columna no nula
	private String nombre;
	
	//@NotEmpty(message = "campo apellido no puede estar vacio") @Size( min = 3, max = 100, message = "campo apellido error")
	@Column (name = "apell" ,nullable = false) // columna no nula
	private String apellido;
	
	//@Min(value = 0)
	@Column (name = "eda" ,nullable = false) // columna no nula
	private int edad;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM-dd-yyyy",timezone = "America/Lima")// formato de fecha
	@Column (name = "nacimiento")
	private Date nacimiento;
	
	//@NotEmpty(message = "campo direccion no puede estar vacio") @Size( min = 5, max = 300, message = "campo nombre error")
	@Column (name = "direc" ,nullable = false) // columna no nula
	private String direccion;
	
	
	@Column (name = "img")
	private String urlimg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id", nullable = false)
	private Curso curso;

	
	
	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
				+ ", nacimiento=" + nacimiento + ", direccion=" + direccion + ", urlimg=" + urlimg + ", curso=" + curso
				+ "]";
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



	public Date getNacimiento() {
		return nacimiento;
	}



	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
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



	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	

}
