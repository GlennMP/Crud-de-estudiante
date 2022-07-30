package pe.edu.cibertec.CrudEstudiante.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pe.edu.cibertec.CrudEstudiante.model.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Entity // crea la tabla 
@Table(name = "curso", uniqueConstraints = {@UniqueConstraint(columnNames = {"nomcurs"})})//significa que esta columna nombre tiene que ser unica
public class Curso {
	
	   
		@Id
		@GeneratedValue( strategy = GenerationType.IDENTITY)  //id auto incrementada
		private long id;
		
		//@NotEmpty(message = "campo nombre del curso no puede estar vacio") @Size( min = 4, max = 255, message = "campo nombre del curso error")
		@Column (name = "nomcurs",nullable = false)// columna no nula
		private String nombreCurso;
		
		@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
		private Set<Estudiante> estudiantes = new HashSet<>();
		
		
		@Override
		public String toString() {
			return "Curso [id=" + id + ", nombreCurso=" + nombreCurso + "]";
		}
		
		

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getNombreCurso() {
			return nombreCurso;
		}

		public void setNombreCurso(String nombreCurso) {
			this.nombreCurso = nombreCurso;
		}
		
		
		//EAGER para cargar info en este dato cuando se aga un listado de un estudiante
		//LAZY para no cargar info en este dato cuadno se aga un listado de un estudiante recomendado si implementas la consulta en el repo
		//mappedby es para hacer la relacion de una tabla a otra
		//@OneToMany(fetch = FetchType.LAZY, mappedBy = "curso" )
		//private List<Estudiante> estudiantes;
}
