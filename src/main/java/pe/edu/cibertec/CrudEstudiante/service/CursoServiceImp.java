package pe.edu.cibertec.CrudEstudiante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.CrudEstudiante.Excepciones.NotFoundException;
import pe.edu.cibertec.CrudEstudiante.Repo.CursoRepository;
import pe.edu.cibertec.CrudEstudiante.model.Curso;


@Service
public class CursoServiceImp implements CursoService{

	@Autowired
	private CursoRepository repoCurso;
	
	@Override
	@Transactional
	public Curso guardar(String nombreCurso) {
		
		Curso cur = new Curso();
		cur.setNombreCurso(nombreCurso);
		Curso nuevoCurso = repoCurso.save(cur);
		return nuevoCurso;
		
	}

	@Override
	@Transactional
	public void eliminarPorId(long id) {
		repoCurso.deleteById(id);
	}

	@Override
	@Transactional
	public Curso actualizarCurso(long id, String nombreCurso) {
		
		Curso cur = new Curso();
		cur.setId(id);
		cur.setNombreCurso(nombreCurso);
		Curso actualizaCurso = repoCurso.save(cur);
		return actualizaCurso;
		
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public List<Curso> listaDeCursos(int numPagina, int cantidadColum,String filtrarPor) {
		
		Pageable pageable = PageRequest.of(numPagina, cantidadColum,Sort.by(filtrarPor));
		Page<Curso> cursos = repoCurso.findAll(pageable);
		List<Curso> curso = cursos.getContent();
		return curso;
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public Curso listarPorId(long id) {
		Curso curso = repoCurso.findById(id).orElseThrow(()-> new NotFoundException("Curso", "id", id));
		return curso;
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public Curso listarPorNombre(String nombre) {
		Curso curso = repoCurso.findByNombreCurso(nombre);
		return curso;
	}

}
