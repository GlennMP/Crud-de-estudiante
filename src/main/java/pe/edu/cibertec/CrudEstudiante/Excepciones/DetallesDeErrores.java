package pe.edu.cibertec.CrudEstudiante.Excepciones;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DetallesDeErrores {
	
	
	private Date tiempo;
	private String mensajes;
	private String detalleError;
	
	
	public DetallesDeErrores(Date tiempo, String mensajes, String detalleError) {
		super();
		this.tiempo = tiempo;
		this.mensajes = mensajes;
		this.detalleError = detalleError;
	}
	
	
	

}
