package pe.edu.cibertec.CrudEstudiante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
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
		return repoCurso.save(cur);
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
		return repoCurso.save(cur);
	}

}
