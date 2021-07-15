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

@Entity(name = "Veterinario")
@Table(name = "VETERINARIO", schema = "PETS")
@NamedQueries({
		@NamedQuery(name = "Veterinario.allVeterinario", query = "select v from Veterinario v order by v.id desc") })
public class Veterinario extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, name = "veterinario_seq", schema = "PETS", sequenceName = "veterinario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "veterinario_seq")
	@Column(name = "ID", columnDefinition = "NUMBER(19,0)")
	private Long id;

	@Column(name = "NOME", columnDefinition = "VARCHAR2(70)")
	private String nome;

	@Column(name = "TELEFONE", columnDefinition = "VARCHAR2(15)")
	private String telefone;

	@Column(name = "EMAIL", columnDefinition = "VARCHAR2(70)")
	private String email;

	@OneToMany(targetEntity = Consulta.class, mappedBy = "veterinario", fetch = FetchType.LAZY)
	private List<Consulta> listaConsulta;

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

	public List<Consulta> getListaConsulta() {
		return listaConsulta;
	}

	public void setListaConsulta(List<Consulta> listaConsulta) {
		this.listaConsulta = listaConsulta;
	}

}
