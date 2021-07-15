package br.com.clinicaanimal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.clinicaanimal.model.base.AbstractEntity;

@Entity(name = "Tutor")
@Table(name = "TUTOR", schema = "PETS")
@NamedQueries({ @NamedQuery(name = "Tutor.allTutor", query = "select t from Tutor t order by t.id desc") })
public class Tutor extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, name = "tutor_seq", schema = "PETS", sequenceName = "tutor_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "tutor_seq")
	@Column(name = "ID", columnDefinition = "NUMBER(19,0)")
	private Long id;

	@Column(name = "NOME", columnDefinition = "VARCHAR2(70)")
	private String nome;

	@Column(name = "TELEFONE", columnDefinition = "VARCHAR2(15)")
	private String telefone;

	@Column(name = "EMAIL", columnDefinition = "VARCHAR2(70)")
	private String email;

	@OneToMany(targetEntity = Animal.class, mappedBy = "tutor", fetch = FetchType.LAZY)
	private List<Animal> listaAnimal;

	/*--------
	 * get/set
	 --------*/
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Animal> getListaAnimal() {
		return listaAnimal;
	}

	public void setListaAnimal(List<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}

}
