package com.stockmanagment.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="salle")
public class Room {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(name = "libelle")
	private String libelle ;
	@Column(name="code")
	private String codeSalle ;

	// plusieurs salles appartient à un departement
	@ManyToOne
	@JoinColumn(name="id_departement")
	private Departement departement ;
	@OneToMany(mappedBy = "salle")
	private List<Equipement> equipements;
}
