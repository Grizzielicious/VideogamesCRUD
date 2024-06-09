package org.grizziesoft.VideogamesCRUD.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static org.grizziesoft.VideogamesCRUD.Constants.ValidationsConstants.CANNOT_BE_NULL_OR_EMPTY;
import static org.grizziesoft.VideogamesCRUD.Constants.ValidationsConstants.LENGHT_NOT_VALID25;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="genero")
public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_genero")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int idGenero;
	
	@NotEmpty(message = "descripcion" + CANNOT_BE_NULL_OR_EMPTY)
	@Size(min=3, max=25, message="descripcion" + LENGHT_NOT_VALID25)
	@Column(name="descripcion")
	private String descripcionGenero;
	
	
	
}
