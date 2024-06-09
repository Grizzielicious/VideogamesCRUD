package org.grizziesoft.VideogamesCRUD.Service;

import java.util.List;
import java.util.Optional;

import org.grizziesoft.VideogamesCRUD.dto.Genero;

public interface GeneroService {
	public List<Genero> todosLosGeneros();
	public Optional<Genero> encontrarPorGenero(String genero);
	public void crearGenero (Genero genero);
	public void borrarGenero (Genero genero);
}
