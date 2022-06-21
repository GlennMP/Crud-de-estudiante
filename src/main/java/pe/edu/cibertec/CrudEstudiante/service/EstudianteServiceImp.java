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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
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
	
	
	
	@Override
	@Transactional(readOnly = true) // solo lectura
	public List<Estudiante> listado(Sort sort) {
		return repoestudi.findAll(sort);
	}

	@Override
	@Transactional(readOnly = true) // solo lectura
	public Estudiante listadoPorId(long id) {
		return repoestudi.findById(id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , id)); // utilizamos la excepcion personalizada que creamos para filtrar por id
	}

	@Override
	@Transactional
	public void eliminar(long id) {
		repoestudi.deleteById(id);
	}

	@Override
	@Transactional 
	public Estudiante actualizarPostulante(long id ,String nombre,String apellido, int edad, String direccion, long curso) {
		
		Estudiante estud = repoestudi.findById(id).orElseThrow(() -> new NotFoundException("Estudiante", "id" , id)); // utilizamos la excepcion personalizada que creamos para filtrar por id;
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(curso);
		return estud;
	}

	@Override
	@Transactional 
	public Estudiante agregarPostulante(String nombre, String apellido, int edad, String direccion, long curso) {
		
		
		
		Estudiante estud = new Estudiante();
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(curso);
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
	
	
    
}
