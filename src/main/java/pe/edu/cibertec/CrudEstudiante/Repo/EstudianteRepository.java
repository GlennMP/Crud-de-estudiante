package pe.edu.cibertec.CrudEstudiante.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
	
	Estudiante findEstudianteById(long Id);
	Estudiante findByNombre(String nombre);
}
