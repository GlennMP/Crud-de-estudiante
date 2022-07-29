package pe.edu.cibertec.CrudEstudiante.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	Curso findByNombreCurso(String nombreCurso); // filtrado por nombre
	//Curso findCursoById(long id);

}
