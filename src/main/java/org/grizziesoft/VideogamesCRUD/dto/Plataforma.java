package org.grizziesoft.VideogamesCRUD.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="plataforma")
public class Plataforma implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_plataforma")
	@JsonIgnore
	private int idPlataforma;

	@Column(name="nombre_plataforma")
	private String nombrePlataforma;
	
	@ManyToMany(mappedBy = "plataformas")
	private List<Videojuego> videojuegos;
}