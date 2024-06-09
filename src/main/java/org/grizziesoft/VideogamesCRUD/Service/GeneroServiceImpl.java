package org.grizziesoft.VideogamesCRUD.Service;

import java.util.List;
import java.util.Optional;

import org.grizziesoft.VideogamesCRUD.dto.Genero;
import org.grizziesoft.VideogamesCRUD.repositories.GeneroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService{

	@Autowired
	private GeneroDao repository;
	
	@Override
	public List<Genero> todosLosGeneros() {
		return repository.findAllByOrderByDescripcionGenero();
	}

	@Override
	public Optional<Genero> encontrarPorGenero(String genero) {
		return repository.findByDescripcionGenero(genero);
	}

	@Override
	public void crearGenero(Genero genero) {
		repository.saveAndFlush(genero);
	}

	@Override
	public void borrarGenero(Genero genero) {
		repository.delete(genero);
	}
	
}
