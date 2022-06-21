package pe.edu.cibertec.CrudEstudiante.service;

import pe.edu.cibertec.CrudEstudiante.model.Curso;

public interface CursoService {

	Curso guardar(String nombreCurso);

	void eliminarPorId(long id);
	
	Curso actualizarCurso(long id,String nombreCurso);

}
