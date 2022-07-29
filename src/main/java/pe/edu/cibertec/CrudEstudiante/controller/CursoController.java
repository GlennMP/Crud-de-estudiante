package pe.edu.cibertec.CrudEstudiante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.service.CursoService;
import pe.edu.cibertec.CrudEstudiante.utilerias.Constantes;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {
	
	@Autowired
	private CursoService serviceCurso;
	
	@PostMapping("/guardar")
	public ResponseEntity<Curso> guardarCurso(@RequestParam String nombre){
		Curso curso = serviceCurso.guardar(nombre);
		if(curso != null) {
			return new ResponseEntity<>(curso,HttpStatus.CREATED);			
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Curso> editarCurso(@RequestParam long id, @RequestParam String nombre){
		
		Curso curso = serviceCurso.actualizarCurso(id, nombre);
		
		if(curso != null) {
			return new ResponseEntity<Curso>(curso,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Curso>> lista(@RequestParam(value = "numPagina",defaultValue = Constantes.LISTAR_POR_NUMERO_DE_PAGINA,required = false) int numPagina,
			@RequestParam(value = "cantidadColum",defaultValue = Constantes.LISTAR_CANTIDAD_DE_COLUMNAS,required = false) int cantidadColum, 
			@RequestParam(value = "filtrarPor", defaultValue = Constantes.LISTA_DE_CURSOS_ORDENADA_POR,required = false) String filtrarPor){
		
		//el listado se mostrara con un numero de paginas y cantidad de columnas que se va a mostrar
		List<Curso> listaCurso = serviceCurso.listaDeCursos(numPagina,cantidadColum,filtrarPor);
		
		if(listaCurso.size() >0) {
			return new ResponseEntity<List<Curso>>(listaCurso,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}	
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminar(@PathVariable long id){
		
		Curso curso = serviceCurso.listarPorId(id);
		
		if(curso != null) {
			
			serviceCurso.eliminarPorId(curso.getId());
			return new ResponseEntity<>("Curso "+curso.getNombreCurso()+" eliminado.",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@GetMapping("/lisxnom")
	public ResponseEntity<Curso> listarPorNombre(@RequestParam String nombre){
		
		Curso curso = serviceCurso.listarPorNombre(nombre);
		
		if(curso != null) {
			return new ResponseEntity<Curso>(curso,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
