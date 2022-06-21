package pe.edu.cibertec.CrudEstudiante.Excepciones;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionGeneral extends ResponseEntityExceptionHandler {

	
	
	
	
	
	//exception para manejar los errores de los campos que no cumplen los colocado en las tablas de la bd
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errores = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String nombreCampo =((FieldError)error).getField(); // tenemos el nombre del campo donde se dio el error
			String mesaje = error.getDefaultMessage(); // tenemos el mesaje que dio el error
			errores.put(nombreCampo, mesaje);
		});
		return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
	}
	
}
