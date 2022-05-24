package pe.edu.cibertec.CrudEstudiante.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	//PostMapping te invian info
	//GetMapping  dar info
	//PathVariable para estableces una variable 
	//RequestParam un parametro para el objeto
	//RequestBody para un objeto entero
	
	//rutas de busqueda 
	//System.getProperty("user.dir"); 
	//System.getProperty("user.name");
	//System.getProperty("os.name");
	//System.getProperty("os.version");
	
	
	
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<Estudiante>> listar(){
		//ordenar por nombre
		Sort sortnombre = Sort.by("nombre");
		//listado con un orden por nombre
		List<Estudiante> estudiante= serviceestudi.listado(sortnombre);
		//si el list del objeto estudiante esta llena
		if(estudiante.size()>0) {
			return new ResponseEntity<List<Estudiante>>(estudiante, HttpStatus.OK);
	    //si el list del objeto estudiante no esta llena
		}else {
			return new ResponseEntity<List<Estudiante>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@PostMapping("/guardar")
	public ResponseEntity<Estudiante> guardarEstudiante(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido, @RequestParam("edad") int edad, @RequestParam("direccion") String direccion, @RequestParam("curso") long curso, @RequestParam MultipartFile urlimg){
		// lo guardado se ingresa es este objeto
		Estudiante nuevoEstudiante = serviceestudi.agregarPostulante(nombre, apellido, edad, direccion, curso,null);
		serviceestudi.guardarimg(nuevoEstudiante, urlimg);
		// el objeto no esta null
		if(nuevoEstudiante != null) {
			return new ResponseEntity<Estudiante>(HttpStatus.CREATED);
		// el objeto esta null	
		}else {
			return new ResponseEntity<Estudiante>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@PostMapping("/editar/{id}")
	public ResponseEntity<Estudiante> editarPostulante (@PathVariable long id ,@RequestParam String name, @RequestParam String apellido, @RequestParam int edad, @RequestParam String direccion,@RequestParam long curso, @RequestParam MultipartFile image){
		
		Estudiante editarestudiante=serviceestudi.actualizarPostulante(id, name, apellido, edad, direccion, curso, null);        
		serviceestudi.guardarimg(editarestudiante, image);
		// el objeto no esta null
				if(editarestudiante != null) {
					return new ResponseEntity<Estudiante>(HttpStatus.CREATED);
				// el objeto esta null	
				}else {
					return new ResponseEntity<Estudiante>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
	}
	
	
	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<Estudiante> filtrado(@PathVariable String nombre){
		
		 Estudiante filtroEstudiante = serviceestudi.findEstudianteByNombre(nombre);
		
		if(filtroEstudiante != null) {
			return new ResponseEntity<Estudiante>(filtroEstudiante,HttpStatus.OK);
		}else {
			return new ResponseEntity<Estudiante>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	
}
