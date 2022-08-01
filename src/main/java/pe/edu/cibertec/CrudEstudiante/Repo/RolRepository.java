package pe.edu.cibertec.CrudEstudiante.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.CrudEstudiante.model.Rol;

public interface RolRepository extends JpaRepository<Rol,Long>{

	public Optional<Rol> findByNombre(String nombre);
}
