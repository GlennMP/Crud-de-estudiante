package pe.edu.cibertec.CrudEstudiante.controller;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;
import pe.edu.cibertec.CrudEstudiante.service.EstudianteService;

@RestController
@RequestMapping(value = "/estudiante")
public class EstudianteController {

	@Autowired
	private EstudianteService serviceestudi;

	// PostMapping te invian info
	// GetMapping dar info
	// PutMapping para actualizar
	// DeleteMapping eliminar
	
	// PathVariable para estableces una variable
	// RequestParam un parametro para el objeto
	// RequestBody para un objeto entero

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
	public ResponseEntity<List<Estudiante>> listar() {
		// ordenar por nombre
		Sort sortnombre = Sort.by("nombre");
		// listado con un orden por nombre
		List<Estudiante> estudiante = serviceestudi.listado(sortnombre);
		// si el list del objeto estudiante esta llena
		if (estudiante.size() > 0) {
			return new ResponseEntity<List<Estudiante>>(estudiante, HttpStatus.OK);
			// si el list del objeto estudiante no esta llena
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/guardar")     // se le agregar valid para que cumpla los requisitu de los campos de la tabla
	public ResponseEntity<Estudiante> guardarEstudiante(@Valid @RequestParam String nombre,
			@Valid @RequestParam String apellido,@Valid @RequestParam int edad,
			@Valid @RequestParam String direccion,@Valid @RequestParam long curso,
			@Valid @RequestParam(name = "urlimg")  MultipartFile urlimg) {
		// lo guardado se ingresa es este objeto
		Estudiante nuevoEstudiante = serviceestudi.agregarPostulante(nombre, apellido, edad, direccion, curso);
		serviceestudi.guardarimg(nuevoEstudiante, urlimg);
		// el objeto no esta null
		if (nuevoEstudiante != null) {
			return new ResponseEntity<>(HttpStatus.CREATED);
			// el objeto esta null
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/editar")           // se le agregar valid para que cumpla los requisitu de los campos de la tabla
	public ResponseEntity<Estudiante> editarPostulante(@Valid @RequestParam(name = "id") Long id,@Valid @RequestParam String nombre,
			@Valid @RequestParam String apellido,@Valid @RequestParam int edad,
			@Valid @RequestParam String direccion,@Valid @RequestParam long curso,@Valid @RequestParam MultipartFile urlimg) { 
		
		Estudiante editarestudiante = serviceestudi.actualizarPostulante(id,nombre,apellido,edad,direccion,curso);
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
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Estudiante> eliminar(@PathVariable long id){
		Estudiante filtroEstudiante = serviceestudi.listadoPorId(id);
		serviceestudi.eliminar(filtroEstudiante.getId());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
