package pe.edu.cibertec.CrudEstudiante.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

public interface EstudianteService {
	
	List<Estudiante> listado(int numPagina, int cantidadColum,String filtrarPor);
	
	Estudiante listadoPorId(long id);
	
	Estudiante filtrarEstudiantePorNombre(String name);
	
	void eliminar (long id); // no retornas
	
	void guardarimg(Estudiante Estudiante, MultipartFile imgpostulante); // no retornas
	
	Estudiante actualizarPostulante( long id ,String nombre,String apellido, int edad, String direccion, long curso_id);
	
	Estudiante agregarPostulante(long curso_id,String nombre,String apellido, int edad, String direccion);
	
	List<Estudiante> obtenerEstudiantesPorCurso(long cursoId);
	
	Estudiante obtenerEstudiantePorId(long cursoId, long estudianteId);
	    
	

}
