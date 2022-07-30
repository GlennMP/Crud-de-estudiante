package pe.edu.cibertec.CrudEstudiante.service;

import java.util.List;
import pe.edu.cibertec.CrudEstudiante.model.Curso;

public interface CursoService {

	Curso guardar(String nombreCurso);
	void eliminarPorId(long id);
	List<Curso> listaDeCursos(int numPagina, int cantidadColum,String filtrarPor);
	Curso listarPorId (long id);
	Curso listarPorNombre (String nombre);
	Curso actualizarCurso(long id,String nombreCurso);


}
