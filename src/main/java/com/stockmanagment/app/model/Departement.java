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
@Table(name="Departement")
public class Departement {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nom")
	private String nom;
	@JsonIgnore
	@OneToMany(mappedBy = "departement")
	private List<Room> rooms;
	@JsonIgnore
	@OneToMany(mappedBy = "departement")
	private List<Users> users;
}
