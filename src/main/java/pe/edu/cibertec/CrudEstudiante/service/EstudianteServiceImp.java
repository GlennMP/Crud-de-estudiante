package pe.edu.cibertec.CrudEstudiante.service;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import pe.edu.cibertec.CrudEstudiante.Repo.CursoRepository;
import pe.edu.cibertec.CrudEstudiante.Repo.EstudianteRepository;
import pe.edu.cibertec.CrudEstudiante.model.Curso;
import pe.edu.cibertec.CrudEstudiante.model.Estudiante;

@Slf4j
@Service
public class EstudianteServiceImp implements EstudianteService {
	
	@Autowired
	private EstudianteRepository repoestudi;
	
	private static String ruta = "C://temp//uploads//";

	
	// readOnly = true solo de lectura
	@Override
	@Transactional(readOnly = true)
	public List<Estudiante> listado(Sort sort) {
		return repoestudi.findAll(sort);
	}

	@Override
	@Transactional(readOnly = true)
	public Estudiante listadoPorId(long id) {
		return repoestudi.getById(id);
	}

	@Override
	@Transactional
	public void eliminar(long id) {
		repoestudi.deleteById(id);
	}

	@Override
	@Transactional
	public Estudiante actualizarPostulante(long id, String nombre, String apellido, int edad, String direccion,
			long curso, MultipartFile urlimg) {
		
		Estudiante estud = repoestudi.findEstudianteById(id);
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(curso);
		estud.setUrlimg(getTemporaryProfileImageUrl(nombre));
		repoestudi.save(estud);
		try {
			guardarimg(estud, urlimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estud;
	}

	@Override
	@Transactional
	public Estudiante agregarPostulante(String nombre, String apellido, int edad, String direccion, long curso,
			MultipartFile urlimg) {
		
		Estudiante estud = new Estudiante();
		
		estud.setNombre(nombre);
		estud.setApellido(apellido);
		estud.setEdad(edad);
		estud.setDireccion(direccion);
		estud.setCurso(curso);
		estud.setUrlimg(getTemporaryProfileImageUrl(nombre));
		repoestudi.save(estud);
		try {
			guardarimg(estud, urlimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return estud;
	}
	
	//
	private String setImageUrl (String nombreusuario) {
	    return ServletUriComponentsBuilder.fromCurrentContextPath().path(ruta + nombreusuario + "/" + nombreusuario + ".jpg" ).toUriString();
	}
	
	//
	private String getTemporaryProfileImageUrl(String username) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(ruta+username).toUriString();
    }
	
	
    private void guardarimg(Estudiante estudiante, MultipartFile imgpostulante)throws IOException {
    	
    	if(!imgpostulante.isEmpty()) {
    		//ruta de la creacion de la carpeta
    		Path rutaexactadefoto = Paths.get(ruta + "//" +estudiante.getNombre()+" "+ estudiante.getApellido()).toAbsolutePath().normalize();
    		if (!Files.exists(rutaexactadefoto)){
    			
    			// crea la carpeta dicha arriba
                Files.createDirectories(rutaexactadefoto);
               // Log.info("Directorio creado:"+rutaexactadefoto.toString());
            }
    		
    		// aqui puede que este el problema
    		Files.deleteIfExists(Paths.get(rutaexactadefoto+".jpg"));
            Files.copy(imgpostulante.getInputStream(),rutaexactadefoto.resolve(rutaexactadefoto+"//"+estudiante.getNombre()+" "+ estudiante.getApellido()+".jpg"),REPLACE_EXISTING);
            estudiante.setUrlimg(setImageUrl(estudiante.getNombre()));
            repoestudi.save(estudiante);
            log.info("Archivo guardado en el sistema"+imgpostulante.getOriginalFilename());	
    	}
    }

	@Override
	@Transactional(readOnly = true)
	public Estudiante  findEstudianteByNombre(String name) {
		
		return repoestudi.findEstudianteByNombre(name);
	}
    
}
