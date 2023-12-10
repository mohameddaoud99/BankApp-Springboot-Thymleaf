package tn.iit.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "t_client")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Include
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_client", length = 50, nullable = false)
	private int id;
	@Column(name = "nom_client", length = 50, nullable = false)
	private String nom;
	@Column(name = "prenom_client", length = 50, nullable = false)
	private String prenom;
	@Column(name = "adresse_client", length = 150, nullable = false)
	private String adresse;
	@JsonIgnore
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Compte> comptes;
	
	public String getLabel()
	{
		return nom + " " + prenom;
	}
	
	public String getValue()
	{
		return nom + " " + prenom;
	}
}
