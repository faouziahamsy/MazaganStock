package com.stockmanagment.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="equipement")
public class Equipement {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(name ="quantity")
	private Long quantity ;
	@Column(name = "matricule")
	private String matricule;
	//@Lob @Column(name="photo",columnDefinition = "MEDIUMBLOB")
	//private String photoEquipement;
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category ;
	@ManyToMany(mappedBy = "equipements")
	private List<Article> articles;
	@ManyToOne
	@JoinColumn(name = "id_salle")
	private Room salle;
	@Column(name="etat")
	@Enumerated(EnumType.STRING) // Indique à JPA d'utiliser le nom de l'énuméré dans la base de données
	private EtatEquipement etat;
	private Instant date_entree;
	private Instant date_sortie;
}


