package pe.edu.cibertec.CrudEstudiante.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.CrudEstudiante.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
