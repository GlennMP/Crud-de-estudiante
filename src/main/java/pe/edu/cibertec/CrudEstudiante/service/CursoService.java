package pe.edu.cibertec.CrudEstudiante.service;

import pe.edu.cibertec.CrudEstudiante.model.Curso;

public interface CursoService {
	
	public Curso guardar(Curso curos);
	
	public void eliminarPorId(long id);

}
