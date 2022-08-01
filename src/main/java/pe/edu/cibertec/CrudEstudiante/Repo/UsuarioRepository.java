package pe.edu.cibertec.CrudEstudiante.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.CrudEstudiante.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findByUsermaneOrEmail(String usermane, String email);
	public Optional<Usuario> findByUsermane(String usermane);
	public Boolean existsByUsermane(String usermane);
	public Boolean existsByEmail(String email);
	

}
