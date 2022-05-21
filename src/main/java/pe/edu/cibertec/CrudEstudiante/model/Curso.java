package pe.edu.cibertec.CrudEstudiante.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import pe.edu.cibertec.CrudEstudiante.model.*;

import java.util.List;

@Entity
@Table(name = "Curso")
public class Curso {
	
	    //id auto incrementada
		@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
		@Column (name = "id_curso")
		private long id;
		
		//@NotEmpty(message = "campo nombre del curso no puede estar vacio") @Size( min = 4, max = 255, message = "campo nombre del curso error")
		@Column (name = "nomcurs")
		private String nombreCurso;
		
		
		//EAGER para cargar info en este dato cuando se aga un listado de un estudiante
		//LAZY para no cargar info en este dato cuadno se aga un listado de un estudiante recomendado si implementas la consulta en el repo
		//mappedby es para hacer la relacion de una tabla a otra
		//@OneToMany(fetch = FetchType.LAZY, mappedBy = "curso" )
		//private List<Estudiante> estudiantes;

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

		@Override
		public String toString() {
			return "Curso [id=" + id + ", nombreCurso=" + nombreCurso + "]";
		}

}
