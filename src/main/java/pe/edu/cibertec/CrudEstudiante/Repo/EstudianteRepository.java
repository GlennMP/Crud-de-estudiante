package pe.edu.cibertec.CrudEstudiante.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
	
	Estudiante findEstudianteById(long Id); // filtrado por id
	Estudiante findByNombre(String nombre); // filtrado por nombre 
	Estudiante findByNombreAndApellido(String nombre, String apellido); // filtrado por nombre y apellido
	List<Estudiante> findByCursoId(long cursoId);
}
