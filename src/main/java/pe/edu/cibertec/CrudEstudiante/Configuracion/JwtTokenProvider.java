package pe.edu.cibertec.CrudEstudiante.Configuracion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import pe.edu.cibertec.CrudEstudiante.Excepciones.AppException;


@Component
public class JwtTokenProvider {

	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	//generador de token
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fehcaActual = new Date();
		Date fechaExpiracion = new Date(fehcaActual.getTime()+ jwtExpirationInMs);
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return token;		
	}
	
	//obtener usuario
	public String obtenerUserNameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	//verifica que el token este correcto
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Firma de JWT no valida");
		}catch (MalformedJwtException ex) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token de JWT no valida");
		}catch (ExpiredJwtException ex) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token de JWT caducado");
		}catch (UnsupportedJwtException ex) {
			throw new AppException(HttpStatus.BAD_REQUEST, "Token de JWT no compatible");
		}catch (IllegalArgumentException ex) {
			throw new AppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
		}
		
	}
}
