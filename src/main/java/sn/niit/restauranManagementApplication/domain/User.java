package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotEmpty
	private String prenom;
	@NotEmpty
	private String nom;

	@NotEmpty(message = "L'email de l'utlisateur est obligatoire ")
	private String email;

	@NotEmpty(message = "Le mot de pass  de l'utlisateur  est obligatoire ")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinColumn(name="roles", referencedColumnName="roleId")
	private Collection<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(Long id, String prenom, String nom, String email, String password) {

		this.prenom = prenom;
		this.prenom = nom;
		this.email = email;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
