package pe.edu.cibertec.CrudEstudiante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // crea la tabla 
@Table(name = "rol")
public class Rol {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)  //id auto incrementada
	private long id;
	
	@Column (name = "nom" ,length = 60) 
	private String nombre;
	
	
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
	
	

}
