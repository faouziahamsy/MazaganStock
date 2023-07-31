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
	@Column(name ="reference")
	private String reference ;
	@Column(name="codeBarre")
	private String codeBarre;
	@Column(name = "matricule")
	private String matricule;
	@Column(name="photo")
	private String photoArticle;
	private Instant date_entree;
	private Instant date_sortie;

	@ManyToMany
	@JoinTable( name = "equipement_article", joinColumns = @JoinColumn(name = "id_article"), inverseJoinColumns = @JoinColumn(name = "id_equipement") )
	private List<Equipement> equipements;
}
