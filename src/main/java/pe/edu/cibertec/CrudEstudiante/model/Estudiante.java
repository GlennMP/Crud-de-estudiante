package pe.edu.cibertec.CrudEstudiante.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity // crea la tabla 
@Table(name = "Estudiante")
public class Estudiante {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY) //id auto incrementada
	@Column (name = "id_estudiante")
	private long id;
	
	@NotEmpty(message = "campo nombre no puede estar vacio") @Size( min = 2, max = 50, message = "campo nombre error")
	@Column (name = "nom" ,nullable = false) // columna no nula
	private String nombre;
	
	@NotEmpty(message = "campo apellido no puede estar vacio") @Size( min = 2, max = 80, message = "campo apellido error")
	@Column (name = "apell" ,nullable = false) // columna no nula
	private String apellido;
	
	@Min(value = 18)
	@Column (name = "eda" ,nullable = false) // columna no nula
	private int edad;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "MM-dd-yyyy",timezone = "America/Lima")// formato de fecha
	@Column (name = "nacimiento")
	private Date nacimiento;
	
	@NotEmpty(message = "campo direccion no puede estar vacio") @Size( min = 5, max = 300, message = "campo direccion error")
	//@Email
	@Column (name = "direc" ,nullable = false) // columna no nula
	private String direccion;
	
	
	@Column (name = "img")
	private String urlimg;
	
	@Column (name = "id_cur" ,nullable = false)
	private long curso;
	
	//@ManyToOne /*(fetch = FetchType.LAZY)*/
	//@JoinColumn(name = "id_cur",nullable = false)
	//private Curso curso;

	
}
