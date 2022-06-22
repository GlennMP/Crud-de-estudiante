package pe.edu.cibertec.CrudEstudiante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;
import pe.edu.cibertec.CrudEstudiante.service.CursoService;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

	@Autowired
	private CursoService serviceCurso;

	@PostMapping("/guardar")
	private ResponseEntity<Curso> guardar(@RequestParam String nombreCurso) {

		if (nombreCurso != null) {
			Curso cru = serviceCurso.guardar(nombreCurso);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/listar")
	private ResponseEntity<List<Curso>> listar() {

		List<Curso> cur = serviceCurso.listarCurso();
		if (cur.size() > 0) {
			return new ResponseEntity<List<Curso>>(cur, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/editar")
	private ResponseEntity<Curso> editar(@RequestParam long id, @RequestParam String nombre) {

		Curso cur = serviceCurso.listarPorId(id);

		if (cur != null) {

			serviceCurso.actualizarCurso(id, nombre);
			return new ResponseEntity<Curso>(HttpStatus.CREATED);

		} else {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
