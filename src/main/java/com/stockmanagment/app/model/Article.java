package com.stockmanagment.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="article")
public class Article {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING) // Indique à JPA d'utiliser le nom de l'énuméré dans la base de données
	private EtatEquipement etat;
	@Column(name ="quantity")
	private Long quantity ;
	@Column(name = "matricule")
	private String matricule;
	private Instant date_entree;
	private Instant date_sortie;

	@ManyToMany
	@JoinTable( name = "equipement_article", joinColumns = @JoinColumn(name = "id_article"), inverseJoinColumns = @JoinColumn(name = "id_equipement") )
	private List<Equipement> equipements;
}
