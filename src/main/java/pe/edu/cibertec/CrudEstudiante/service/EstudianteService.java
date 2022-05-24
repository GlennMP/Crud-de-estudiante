package pe.edu.cibertec.CrudEstudiante.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

public interface EstudianteService {
	
	List<Estudiante> listado(Sort sort);
	
	Estudiante listadoPorId(long id);
	
	Estudiante findEstudianteByNombre(String name);
	
	void eliminar (long id);
	
	void guardarimg(Estudiante Estudiante, MultipartFile imgpostulante);
	
	Estudiante actualizarPostulante(long id ,String nombre,String apellido, int edad, String direccion, long curso, String urlimg);
	
	Estudiante agregarPostulante(String nombre,String apellido, int edad, String direccion, long curso, String urlimg);
	    
	

}
