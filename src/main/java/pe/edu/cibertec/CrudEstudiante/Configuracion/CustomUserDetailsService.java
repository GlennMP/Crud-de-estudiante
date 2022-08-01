package pe.edu.cibertec.CrudEstudiante.Configuracion;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.CrudEstudiante.Repo.UsuarioRepository;
import pe.edu.cibertec.CrudEstudiante.model.Rol;
import pe.edu.cibertec.CrudEstudiante.model.Usuario;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repoUsuario.findByUsermane(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado : " + username));
		return new User(usuario.getEmail(),usuario.getPassword(), mapearRoles(usuario.getRoloes()));
	}
	
	private Collection<? extends GrantedAuthority>mapearRoles(Set<Rol> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

}
