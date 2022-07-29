package pe.edu.cibertec.CrudEstudiante.service;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.CrudEstudiante.Excepciones.AppException;
import pe.edu.cibertec.CrudEstudiante.Excepciones.NotFoundException;
import pe.edu.cibertec.CrudEstudiante.Repo.CursoRepository;
import pe.edu.cibertec.CrudEstudiante.Repo.EstudianteRepository;
import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;


@Slf4j
@Service
public class EstudianteServiceImp implements EstudianteService {
	
	@Autowired
	private EstudianteRepository repoestudi;
	@Autowired
	private CursoRepository repocurso;
	
	
	
	@Override
	@Transactional(readOnly = true) // solo lectura
	public List<Estudiante> listado(int numPagina, int cantidadColum,String filtrarPor) {
		
		Pageable pageable = PageRequest.of(numPagina, cantidadColum,Sort.by(filtrarPor));
		Page<Estudiante> estudiantes = repoestudi.findAll(pageable);
		List<Estudiante> estudiante = estudiantes.getContent();
		return estudiante;
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public Estudiante listadoPorId(long id) {
		Estudiante estudiante = repoestudi.findById(id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , id)); // utilizamos la excepcion personalizada que creamos para filtrar por id
		return estudiante; 
	}

	@Override
	@Transactional
	public void eliminar(long id) {
		repoestudi.deleteById(id);
	}

	@Override
	@Transactional 
	public Estudiante actualizarPostulante(long id ,String nombre,String apellido, int edad, String direccion, long curso_id) {
		
		Curso cursoporelid = repocurso.findById(curso_id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , curso_id));
		Estudiante estud = repoestudi.findById(id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , id));// utilizamos la excepcion personalizada que creamos para filtrar por id;
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(cursoporelid);
		return estud;
	}
                                                                                                                                                                                                                                                                                                     
	@Override
	@Transactional 
	public Estudiante agregarPostulante(long curso_id,String nombre, String apellido, int edad, String direccion) {
		
	    Curso cursoporelid = repocurso.findById(curso_id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , curso_id));
		
		Estudiante estud = new Estudiante();
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(cursoporelid);
		return repoestudi.save(estud);
		
	}
	
	
	@Override
	@Transactional(readOnly = true) // solo lectura
	public Estudiante  filtrarEstudiantePorNombre(String name) {
		return repoestudi.findByNombre(name);
	}
	
	
	@Override
	public void guardarimg(Estudiante estudiante, MultipartFile imgpostulante) {
		
		if (!Arrays.asList(IMAGE_JPEG_VALUE,IMAGE_PNG_VALUE,IMAGE_GIF_VALUE).contains(imgpostulante.getContentType())){
			log.info(imgpostulante.getOriginalFilename()+"no es una imagen. Por favor, suba una imagen");
        }
		
		if(!imgpostulante.isEmpty()) {
			try {
				Path path = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/").toAbsolutePath().normalize();
				if(!Files.exists(path)) {
					 Files.createDirectories(path);
					 log.info("Directorio creado:"+path.toString());
				}
				Files.deleteIfExists(Paths.get(path+".jpg"));
				Files.copy(imgpostulante.getInputStream(),path.resolve(estudiante.getId()+".jpg"),REPLACE_EXISTING);
				//Files.write(path, bytes);
				estudiante.setUrlimg(path.toString()+"\\"+estudiante.getId()+".jpg");
				repoestudi.save(estudiante);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public List<Estudiante> obtenerEstudiantesPorCurso(long cursoId) {
		List<Estudiante> estidiantes = repoestudi.findByCursoId(cursoId);
		return estidiantes.stream().collect(Collectors.toList());
	}

	@Override
	public Estudiante obtenerEstudiantePorId(long cursoId, long estudianteId) {
		Curso cursoporelid = repocurso.findById(cursoId).orElseThrow(() -> new NotFoundException("Estudiante", "id" , estudianteId));
		Estudiante estudianteporid = repoestudi.findById(estudianteId).orElseThrow(() -> new NotFoundException("Estudiante", "id" , estudianteId));
		
		if(estudianteporid.getCurso().getId() == cursoporelid.getId()) {
			return estudianteporid;
		}else {
			 throw new AppException(HttpStatus.BAD_REQUEST, "El estudiante no pertenece al curso");
		}
	}
	
	
    
}
