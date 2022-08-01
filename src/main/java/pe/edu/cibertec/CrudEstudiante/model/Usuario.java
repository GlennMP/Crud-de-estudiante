package pe.edu.cibertec.CrudEstudiante.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity // crea la tabla 
@Table(name = "usuario",uniqueConstraints = {@UniqueConstraint(columnNames = {"usermane"}),
		@UniqueConstraint(columnNames = {"email"} )})
public class Usuario {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)  //id auto incrementada
	private long id;
	private String nombre;
	private String usermane;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//como es una union de muchos a muchos se crea una tabla extra con las colimnas usuario_id,rol_id
	@JoinTable(name="usuarios_roles",joinColumns = @JoinColumn(name="usuario_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id"))
	private Set<Rol> roloes = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsermane() {
		return usermane;
	}

	public void setUsermane(String usermane) {
		this.usermane = usermane;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoloes() {
		return roloes;
	}

	public void setRoloes(Set<Rol> roloes) {
		this.roloes = roloes;
	}
	
	

}
