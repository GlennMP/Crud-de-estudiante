package pe.edu.cibertec.CrudEstudiante.Excepciones;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus estado;
	private String mensaje;
	
	public AppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}
	
	public AppException(HttpStatus estado, String mensaje,String mensaje2) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.mensaje = mensaje2;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	

}
