package br.com.clinicaanimal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaanimal.model.base.AbstractEntity;

@Entity(name = "Animal")
@Table(name = "ANIMAL", schema = "PETS")
@NamedQueries({ @NamedQuery(name = "Animal.allAnimal", query = "select a from Animal a order by a.id desc") })
public class Animal extends AbstractEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, name = "animal_seq", schema = "PETS", sequenceName = "animal_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "animal_seq")
	@Column(name = "ID", columnDefinition = "NUMBER(19,0)")
	private Long id;

	@Column(name = "NOME", columnDefinition = "VARCHAR2(70)")
	private String nome;

	@Column(name = "ESPECIE", columnDefinition = "VARCHAR2(70)")
	private String especie;

	@Column(name = "RACA", columnDefinition = "VARCHAR2(70)")
	private String raca;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private Date dataNascimento;

	@ManyToOne(targetEntity = Tutor.class, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "ID", columnDefinition = "NUMBER(19,0)", nullable = false, foreignKey = @ForeignKey(name = "tutor_fkey"))
	private Tutor tutor;

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

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

}
