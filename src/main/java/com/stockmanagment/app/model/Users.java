package com.stockmanagment.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String matricule;
	private String email;
	private String password;

	@ManyToOne
	@JoinColumn(name = "id_departement") // Clé étrangère vers la table département
	private Departement departement; // Relation Many-to-One avec la classe Département

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
}
