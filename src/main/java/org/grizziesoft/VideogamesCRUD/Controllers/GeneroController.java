package org.grizziesoft.VideogamesCRUD.Controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.grizziesoft.VideogamesCRUD.CommonsUtils.ErrorUtils;
import org.grizziesoft.VideogamesCRUD.Exceptions.GeneroNotFoundException;
import org.grizziesoft.VideogamesCRUD.Exceptions.InvalidParameterException;
import org.grizziesoft.VideogamesCRUD.Service.GeneroService;
import org.grizziesoft.VideogamesCRUD.dto.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.Errors;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/generos")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;

	@GetMapping("/todosLosGeneros")
	private List<Genero> getTodosLosGeneros() {
		log.info("Obteniendo todos los géneros de la base de datos");
		return generoService.todosLosGeneros();
	}
	
	@GetMapping("/generoEspecifico")
	private Genero getGeneroEspecifico(String genero) {
		log.info("Obteniendo un género en específico de la base de datos");
		return generoService.encontrarPorGenero(genero).orElse(null);
	}
	
	@PostMapping("/crearGenero")
	private ResponseEntity<?> addGenero(@Valid @RequestBody Genero newGenero, Errors errores) {
		ResponseEntity<?> response;
		log.info("Creando un nuevo género...");
		try {
			if(errores.hasErrors()) {
				throw new InvalidParameterException(ErrorUtils.errorsToStringSet(errores).toString());
			}
			generoService.crearGenero(newGenero);
			response = ResponseEntity.ok(newGenero.getDescripcionGenero() + " se ha guarado correctamente en la BD el nuevo género Ü.");
		} catch (Exception e) {
			String detalle = "";
			HttpStatus codigoDeRespuesta;
			if(e instanceof DataIntegrityViolationException) {
				detalle = "Error al insertar un nuevo género en las BD. Detalle: " + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			} else if (e instanceof InvalidParameterException) {
				detalle = e.getMessage();
				codigoDeRespuesta = HttpStatus.BAD_REQUEST;
			} else {
				detalle = "Ocurrió un error no previsto al actualizar los registros" + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response = new ResponseEntity<> (detalle, codigoDeRespuesta);
		}
		return response;
	}
	
	@PutMapping("/actualizacionDeGenero")
	private ResponseEntity<?> actualizarGenero(@RequestParam String genero, @RequestParam String newGenero) {
		log.info("Entrando para actualizar los géneros.");
		ResponseEntity<?> response; 
		try {
			if(Objects.isNull(newGenero) || newGenero.isBlank()) {
				throw new InvalidParameterException("La descripción no puede estar en blanco o ser nula.");
			}
			Optional<Genero> actual = generoService.encontrarPorGenero(genero);
			if(actual.isPresent()) {
				actual.get().setDescripcionGenero(newGenero);
				generoService.crearGenero(actual.get());
				response = ResponseEntity.ok(newGenero + " se ha guardado correctamente en la BD el género correcto Ü.");
			} else {
				throw new GeneroNotFoundException("El género que busca no se encuentra en la BD.");
			}
			
		} catch (Exception e) {
			String detalle = "";
			HttpStatus codigoDeRespuesta;
			if(e instanceof DataIntegrityViolationException) {
				detalle = "Error al insertar un nuevo género en las BD. Detalle: " + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			} else if (e instanceof GeneroNotFoundException) {
				detalle = e.getMessage();
				codigoDeRespuesta = HttpStatus.BAD_REQUEST;
			} else if (e instanceof InvalidParameterException) {
				detalle = e.getMessage();
				codigoDeRespuesta = HttpStatus.BAD_REQUEST;
			} else {
				detalle = "Ocurrió un error no previsto al actualizar los registros" + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response = new ResponseEntity<> (detalle, codigoDeRespuesta);
		}
		return response;
	}
	
	@DeleteMapping("/borrarGenero")
	private ResponseEntity<?> borrarGenero (@RequestParam String genero) {
		log.info("Entrando para borrar géneros.");
		ResponseEntity<?> response; 
		try {
			if(Objects.isNull(genero) || genero.isBlank()) {
				throw new InvalidParameterException("La descripción no puede estar en blanco o ser nula.");
			}
			Optional<Genero> actual = generoService.encontrarPorGenero(genero);
			if(actual.isPresent()) {
				generoService.borrarGenero(actual.get());
				response = ResponseEntity.ok(genero + " se ha borrado correctamente de la BD TT-TT.");
			} else {
				throw new GeneroNotFoundException("El género que busca no se encuentra en la BD.");
			}
		} catch (Exception e){
			String detalle = "";
			HttpStatus codigoDeRespuesta;
			if(e instanceof DataIntegrityViolationException) {
				detalle = "Error al insertar un nuevo género en las BD. Detalle: " + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			} else if (e instanceof GeneroNotFoundException) {
				detalle = e.getMessage();
				codigoDeRespuesta = HttpStatus.BAD_REQUEST;
			} else if (e instanceof InvalidParameterException) {
				detalle = e.getMessage();
				codigoDeRespuesta = HttpStatus.BAD_REQUEST;
			} else {
				detalle = "Ocurrió un error no previsto al actualizar los registros" + e.getMessage();
				codigoDeRespuesta = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			response = new ResponseEntity<> (detalle, codigoDeRespuesta);
		}
		return response;
		
	}
}

