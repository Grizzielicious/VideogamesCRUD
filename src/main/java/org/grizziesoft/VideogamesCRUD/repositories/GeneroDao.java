package org.grizziesoft.VideogamesCRUD.repositories;

import java.util.List;
import java.util.Optional;

import org.grizziesoft.VideogamesCRUD.dto.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroDao extends JpaRepository<Genero, Integer>  {
	
	Optional<Genero> findByDescripcionGenero (String genero);
	List<Genero> findAllByOrderByDescripcionGenero(); //búsqueda inferida, si quiero renombrar mi métodoa mi gusto tengo que definir los queries.
	
}
