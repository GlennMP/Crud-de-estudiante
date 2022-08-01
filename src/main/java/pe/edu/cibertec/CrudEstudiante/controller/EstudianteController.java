package pe.edu.cibertec.CrudEstudiante.controller;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;
import pe.edu.cibertec.CrudEstudiante.service.EstudianteService;
import pe.edu.cibertec.CrudEstudiante.utilerias.Constantes;

@RestController
@RequestMapping(value = "/api/estudiante")
//se utiliza para las validaciones
@Validated
public class EstudianteController {

	@Autowired
	private EstudianteService serviceestudi;

	// PostMapping te invian info
	// GetMapping dar info
	// PutMapping para actualizar
	// DeleteMapping eliminar
	
	// PathVariable para estableces una variable (es enviado por url)
	// RequestParam un parametro para el objeto  (es enviado por url)
	// RequestBody para un objeto entero(es enviado por json)


	// rutas de busqueda
	// System.getProperty("user.dir");
	// System.getProperty("user.name");
	// System.getProperty("os.name");
	// System.getProperty("os.version");

	// 200:ok,300:redireccion,400:error del cliente,500: error del servidor

	@GetMapping(value = "/image/profile/{id}", produces = IMAGE_JPEG_VALUE)
	public byte[] getProfileImage(@PathVariable("id") Long id) throws IOException {
		return Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/" + id + ".jpg"));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Estudiante>> listar(@RequestParam(value = "numPagina",defaultValue = Constantes.LISTAR_POR_NUMERO_DE_PAGINA,required = false) int numPagina,
			@RequestParam(value = "cantidadColum",defaultValue = Constantes.LISTAR_CANTIDAD_DE_COLUMNAS,required = false) int cantidadColum,
			@RequestParam(value = "filtrarPor", defaultValue = Constantes.LISTA_DE_ESTUDIANTES_ORDENADA_POR,required = false) String filtrarPor) {
		//el listado se mostrara con un numero de paginas y cantidad de columnas que se va a mostrar
		List<Estudiante> estudiante = serviceestudi.listado(numPagina,cantidadColum,filtrarPor);

		// si el list del objeto estudiante esta llena
		if (estudiante.size() > 0) {
			return new ResponseEntity<List<Estudiante>>(estudiante, HttpStatus.OK);
			// si el list del objeto estudiante no esta llena
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")//configuracion de accesibilidad
	@PostMapping("/guardar")
	public ResponseEntity<Estudiante> guardarEstudiante(
			@RequestParam 
			@NotEmpty(message = "campo nombre no puede estar vacio") 
			@Size( min = 3, max = 50, message = "campo nombre error") String nombre,
			@RequestParam
			@NotEmpty(message = "campo apellido no puede estar vacio") 
			@Size( min = 3, max = 100, message = "campo apellido error") String apellido, 
			@RequestParam @Min(value = 18) @Max(value = 45) int edad,
			@RequestParam 
			@NotEmpty(message = "campo direccion no puede estar vacio") 
			@Size( min = 5, max = 300, message = "campo direccion error") String direccion, 
			@RequestParam long curso_id,
			@RequestParam(name = "urlimg")  MultipartFile urlimg) {
		// lo guardado se ingresa es este objeto
		Estudiante nuevoEstudiante = serviceestudi.agregarPostulante(curso_id,nombre, apellido, edad, direccion);
		serviceestudi.guardarimg(nuevoEstudiante, urlimg);
		// el objeto no esta null
		if (nuevoEstudiante != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
			// el objeto esta null
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PreAuthorize("hasRole('ADMIN')")//configuracion de accesibilidad
	@PutMapping("/editar")
	public ResponseEntity<Estudiante> editarPostulante(@RequestParam Long id,
			@RequestParam 
			@NotEmpty(message = "campo nombre no puede estar vacio") 
			@Size( min = 3, max = 50, message = "campo nombre error") String nombre,
			@RequestParam 
			@NotEmpty(message = "campo apellido no puede estar vacio") 
			@Size( min = 3, max = 100, message = "campo apellido error") String apellido, 
			@RequestParam @Min(value = 18) @Max(value = 45) int edad,
			@RequestParam 
			@NotEmpty(message = "campo direccion no puede estar vacio") 
			@Size( min = 5, max = 300, message = "campo direccion error") String direccion, 
			@RequestParam long curso_id,@RequestParam MultipartFile urlimg) { 
		
		Estudiante editarestudiante = serviceestudi.actualizarPostulante(id,nombre,apellido,edad,direccion,curso_id);

		serviceestudi.guardarimg(editarestudiante, urlimg);
		// el objeto no esta null
		if (editarestudiante != null) {
			return new ResponseEntity<Estudiante>(HttpStatus.CREATED);
			// el objeto esta null
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/filtrarNom/{nombre}")
	public ResponseEntity<Estudiante> filtrarNom(@PathVariable String nombre) {

		Estudiante filtroEstudiante = serviceestudi.filtrarEstudiantePorNombre(nombre);
		if (filtroEstudiante != null) {
			return new ResponseEntity<Estudiante>(filtroEstudiante, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/filtrarId/{id}")
	public ResponseEntity<Estudiante> filtrarId(@PathVariable long id) {

		Estudiante filtroEstudiante = serviceestudi.listadoPorId(id);
		if (filtroEstudiante != null) {
			return new ResponseEntity<Estudiante>(filtroEstudiante, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")//configuracion de accesibilidad
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable long id){
		Estudiante filtroEstudiante = serviceestudi.listadoPorId(id);
		
		if(filtroEstudiante != null) {
			
			serviceestudi.eliminar(filtroEstudiante.getId());
			return new ResponseEntity<>("Estudiante "+filtroEstudiante.getNombre()+" eliminado.",HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	
	
	@GetMapping("/listEstudinIdCurso/{cursoId}")
	public ResponseEntity<List<Estudiante>>  listDeEstudiantesPorCurso(@PathVariable("cursoId") long cursoId){
		
		List<Estudiante> estudiantes = serviceestudi.obtenerEstudiantesPorCurso(cursoId);
		return new ResponseEntity<List<Estudiante>>(estudiantes,HttpStatus.OK);
		
	}
	
	@GetMapping("/estudinIdCurso/{cursoId}/{estudianteId}")
	public ResponseEntity<Estudiante>  obtenerEstudiantePorElCurso(@PathVariable("cursoId") long cursoId,@PathVariable("estudianteId") long estudianteId){
		
		Estudiante estudiante = serviceestudi.obtenerEstudiantePorId(cursoId, estudianteId);
		
		return new ResponseEntity<Estudiante>(estudiante, HttpStatus.OK);
		
		}
	
	
}
