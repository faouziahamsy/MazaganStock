package com.stockmanagment.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom")
	private String nom;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Equipement> equipements;
}
