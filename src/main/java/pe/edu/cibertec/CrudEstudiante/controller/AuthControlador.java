package pe.edu.cibertec.CrudEstudiante.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.CrudEstudiante.Configuracion.JwtAuthResonse;
import pe.edu.cibertec.CrudEstudiante.Configuracion.JwtTokenProvider;
import pe.edu.cibertec.CrudEstudiante.Excepciones.NotFoundException;
import pe.edu.cibertec.CrudEstudiante.Repo.RolRepository;
import pe.edu.cibertec.CrudEstudiante.Repo.UsuarioRepository;
import pe.edu.cibertec.CrudEstudiante.model.Rol;
import pe.edu.cibertec.CrudEstudiante.model.Usuario;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private RolRepository repoRol;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/inicioSession")
	public ResponseEntity<JwtAuthResonse>inicioDeSession(@RequestParam String usu, @RequestParam  String clave){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usu,clave));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		//obtenemos el token del jwt
		String token = jwtTokenProvider.generarToken(authentication);
		
		return ResponseEntity.ok(new JwtAuthResonse(token));
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<String> registrar(@RequestParam String user,@RequestParam String nombre,@RequestParam String email,
			@RequestParam String password,@RequestParam long id ){
		if(repoUsuario.existsByUsermane(user)) {
			return new ResponseEntity<>("Usuario ingresado ya existe",HttpStatus.BAD_REQUEST);
		}else if(repoUsuario.existsByEmail(email)) {
			return new ResponseEntity<>("Email ingresado ya existe",HttpStatus.BAD_REQUEST);
		}else {
			Usuario usu = new Usuario();
			usu.setNombre(nombre);
			usu.setUsermane(user);
			usu.setEmail(email);
			usu.setPassword(passEncoder.encode(password));
			Rol rols = repoRol.findById(id).orElseThrow(() -> new NotFoundException("Usuario", "id" , id));
			usu.setRoloes(Collections.singleton(rols));
			repoUsuario.save(usu);
			
			return new ResponseEntity<String>("usuario registrado exitosamente",HttpStatus.OK);
		}
	}
	
}
