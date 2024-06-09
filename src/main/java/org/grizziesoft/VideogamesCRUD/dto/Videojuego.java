package org.grizziesoft.VideogamesCRUD.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name="videojuego")
public class Videojuego implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_videojuego")
	@JsonIgnore
	private int idVideojuego;

	@Column(name="nombre_videojuego")
	private String nombreVideojuego;
	
	@Column(name="anio_lanzamiento")
	private int anioLanzamiento;
	
	@Column(name="es_multijugador")
	private boolean esMultijugador;
	
	@ManyToOne
	@JoinColumn(name="id_estudio")
	private Estudio idEstudio;
	
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero idGenero;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name="plataforma_videojuego",
			joinColumns = @JoinColumn(name="id_videojuego", referencedColumnName="id_videojuego", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "id_plataforma", referencedColumnName="id_plataforma", nullable = false)
			)
	private List<Plataforma> plataformas;
}