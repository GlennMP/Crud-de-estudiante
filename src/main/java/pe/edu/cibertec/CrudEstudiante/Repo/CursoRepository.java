package pe.edu.cibertec.CrudEstudiante.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	Curso findCursoById(long id); // filtrado por id
	Curso findByNombreCurso(String nombrecurso); // filtrado por nombre 

}
