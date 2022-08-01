package pe.edu.cibertec.CrudEstudiante.Excepciones;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import pe.edu.cibertec.CrudEstudiante.utilerias.ErrorDetalles;

@ControllerAdvice
public class GlobalExceptionHandler  {

	// maneja los errores de informacion no encontrada
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(NotFoundException exception,
			WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
	}

	// maneja los errores de informacion no encontrada
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorDetalles> manejarResourceAppException(AppException exception, WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
	}

	// maneja los errores de error del servidor
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetalles> manejarResourceGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
