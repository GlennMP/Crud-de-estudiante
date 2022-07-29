package pe.edu.cibertec.CrudEstudiante.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

	/**
	 * Excepcion para cuando busquen por un id y el id no es encontrado 
	 **/
	private static final long serialVersionUID = 1L;
	
	private String nombreDelModel;
	private String nombreDelCampo;
	private long valorDelCampo;
	
	
	public NotFoundException(String nombreDelModel, String nombreDelCampo, long valorDelCampo) {
		super(String.format("%s no encontrado con : %s : '%s'",nombreDelModel,nombreDelCampo,valorDelCampo));
		this.nombreDelModel = nombreDelModel;
		this.nombreDelCampo = nombreDelCampo;
		this.valorDelCampo = valorDelCampo;
	}


	public String getNombreDelModel() {
		return nombreDelModel;
	}


	public void setNombreDelModel(String nombreDelModel) {
		this.nombreDelModel = nombreDelModel;
	}


	public String getNombreDelCampo() {
		return nombreDelCampo;
	}


	public void setNombreDelCampo(String nombreDelCampo) {
		this.nombreDelCampo = nombreDelCampo;
	}


	public long getValorDelCampo() {
		return valorDelCampo;
	}


	public void setValorDelCampo(long valorDelCampo) {
		this.valorDelCampo = valorDelCampo;
	}
	
	
	

}
